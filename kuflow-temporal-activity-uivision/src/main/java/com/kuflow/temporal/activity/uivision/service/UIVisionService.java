/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.temporal.activity.uivision.service;

import com.kuflow.temporal.activity.uivision.dto.UIVisionArgumentDto;
import io.temporal.activity.Activity;
import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UIVisionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UIVisionService.class);

    private static final int WAIT_TO_EMIT_HEARBEAT_MILLISECONDS = 15000;

    public void runMacro(String command, UIVisionArgumentDto uiVisionArgument, Duration executionTimeout) {
        Objects.requireNonNull(command, "command is required");

        Path logFilePath = Paths.get(uiVisionArgument.getLogDirectory(), String.format("uivision-%s.log", Instant.now().toEpochMilli()));

        String processArguments = this.buildProcessArguments(uiVisionArgument, logFilePath);

        ProcessBuilder processBuilder = new ProcessBuilder(command, processArguments);

        try {
            Process process = processBuilder.inheritIO().start();
            Info info = process.info();

            LOGGER.info(
                String.format("Process [%s] started at %s", info.commandLine().orElse(StringUtils.EMPTY), info.startInstant().orElse(null))
            );

            this.checkProcess(process, logFilePath, executionTimeout);
        } catch (IOException e) {
            throw new RuntimeException("Robot process with unexpected error", e);
        }
    }

    /**
     * UI.Vision provides thre custom parameters: cmd_var1,cmd_var2 and cmd_var3
     * We use:
     * - cmd_var1: TaskId
     * - cmd_var2: unused
     * - cmd_var3: any other additional arguments passed from Workflow.
     *
     * @param uiVisionEnvironment
     * @param logFilePath
     * @return
     */
    private String buildProcessArguments(UIVisionArgumentDto uiVisionEnvironment, Path logFilePath) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("autoRunHtml", uiVisionEnvironment.getAutoRunHtml());
        uriVariables.put("pathToLog", logFilePath.toString());
        uriVariables.put("macro", uiVisionEnvironment.getMacro());
        uriVariables.put("closeBrowser", uiVisionEnvironment.getCloseBrowser());
        uriVariables.put("closeRPA", uiVisionEnvironment.getCloseRpa());
        uriVariables.put("cmd_var1", uiVisionEnvironment.getCmdVar1());
        uriVariables.put("cmd_var2", uiVisionEnvironment.getCmdVar2());
        uriVariables.put("cmd_var3", uiVisionEnvironment.getCmdVar3());

        StringSubstitutor sub = new StringSubstitutor(uriVariables);
        String templateString =
            "file:///{autoRunHtml}?macro={macro}&direct=1&savelog={pathToLog}&closeBrowser={closeBrowser}&closeRPA={closeRPA}&cmd_var1={cmd_var1}&cmd_var2={cmd_var2}&cmd_var3={cmd_var3}";

        return sub.replace(templateString);
    }

    private void checkProcess(Process process, Path logFilePath, Duration executionTimeout) throws IOException {
        boolean waitResult = this.waitForJob(process, logFilePath, executionTimeout);
        if (process.isAlive()) {
            this.destroyProcess(process);
        }

        if (!waitResult) {
            throw new RuntimeException(String.format("Bot process [%s] timeout: ", process.info().commandLine().orElse(StringUtils.EMPTY)));
        }

        // When the RPA task finishes without success, return a different value than
        // zero.
        if (process.exitValue() != 0) {
            throw new RuntimeException(
                String.format(
                    "Bot process [%s] finished with undesired status value of %s: ",
                    process.info().commandLine().orElse(StringUtils.EMPTY),
                    process.exitValue()
                )
            );
        }
    }

    private boolean waitForJob(Process process, Path logFilePath, Duration executionTimeout) throws IOException {
        long executionTimeoutLimit = executionTimeout.toMillis();
        long currentWaiting = 0;
        while (!Files.exists(logFilePath) && currentWaiting < executionTimeoutLimit) {
            LOGGER.debug(
                String.format(
                    "Waiting %s sec for robot's job [Total: %s sec]",
                    TimeUnit.MILLISECONDS.toSeconds(WAIT_TO_EMIT_HEARBEAT_MILLISECONDS),
                    TimeUnit.MILLISECONDS.toSeconds(currentWaiting)
                )
            );

            try {
                Thread.sleep(WAIT_TO_EMIT_HEARBEAT_MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(
                    String.format("Bot process [%s] with unexpected error", process.info().commandLine().orElse(StringUtils.EMPTY)),
                    e
                );
            }

            currentWaiting += WAIT_TO_EMIT_HEARBEAT_MILLISECONDS;
            Activity.getExecutionContext().heartbeat(currentWaiting);
        }

        return this.isOkStatus(logFilePath);
    }

    private boolean isOkStatus(Path pathToLog) throws IOException {
        if (Files.exists(pathToLog)) {
            String readFirstLine = com.google.common.io.Files.asCharSource(pathToLog.toFile(), Charset.defaultCharset()).readFirstLine();
            return StringUtils.contains(readFirstLine, "Status=OK");
        }

        return false;
    }

    private void destroyProcess(Process process) {
        try {
            process.destroyForcibly().waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(
                String.format("Cannot destroy bot process [%s]", process.info().commandLine().orElse(StringUtils.EMPTY)),
                e
            );
        }
    }
}
