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
package com.kuflow.rest.operation;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.implementation.RobotOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Robot;
import com.kuflow.rest.model.RobotAssetArchitecture;
import com.kuflow.rest.model.RobotAssetPlatform;
import com.kuflow.rest.model.RobotAssetType;
import com.kuflow.rest.model.RobotFilterContext;
import com.kuflow.rest.model.RobotFindOptions;
import com.kuflow.rest.model.RobotPage;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in RobotOperations. */
public class RobotOperations {

    /** The service. */
    private final RobotOperationsImpl service;

    /**
     * Initializes an instance of RobotOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public RobotOperations(KuFlowClientImpl client) {
        this.service = client.getRobotOperations();
    }

    /**
     * Find all accessible Robots
     * <p>
     * List all the Robots that have been created and the credentials has access.
     * <p>
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<RobotPage> findRobotsWithResponse(RobotFindOptions options, Context context) {
        options = options != null ? options : new RobotFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;
        RobotFilterContext filterContext = options.getFilterContext();

        return this.service.findRobotsWithResponse(size, page, sort, tenantId, filterContext, context);
    }

    /**
     * Find all accessible Robots
     * <p>
     * List all the Robots that have been created and the credentials has access.
     * <p>
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RobotPage findRobots(RobotFindOptions options) {
        return this.findRobotsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Robots
     * <p>
     * List all the Robots that have been created and the credentials has access.
     * <p>
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RobotPage findRobots() {
        return this.findRobotsWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Get a Robot by ID
     * <p>
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Robot> retrieveRobotWithResponse(UUID id, Context context) {
        return this.service.retrieveRobotWithResponse(id, context);
    }

    /**
     * Get a Robot by ID
     * <p>
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Robot retrieveRobot(UUID id) {
        return this.retrieveRobotWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Download robot code
     * <br>
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadRobotSourceCodeWithResponse(UUID id, Context context) {
        return this.service.downloadRobotSourceCodeWithResponse(id, context);
    }

    /**
     * Download robot code
     * <br>
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadRobotSourceCodeWithResponse(UUID id) {
        return this.downloadRobotSourceCodeWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Download robot asset
     * <br>
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadRobotAssetWithResponse(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture,
        Context context
    ) {
        return this.service.downloadRobotAssetWithResponse(id, type, version, platform, architecture, context);
    }

    /**
     * Download robot asset
     * <br>
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadRobotAssetWithResponse(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture
    ) {
        return this.downloadRobotAssetWithResponse(id, type, version, platform, architecture, Context.NONE).getValue();
    }
}
