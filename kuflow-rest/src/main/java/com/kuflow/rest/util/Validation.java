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

package com.kuflow.rest.util;

import com.kuflow.rest.model.Document;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

public class Validation {

    public static void checkDocument(Document document) {
        Objects.requireNonNull(document, "'document' is required");
        Objects.requireNonNull(document.getFileContent(), "'document.fileContent' is required");
        Objects.requireNonNull(document.getFileContent().getLength(), "'document.fileContent.length' is required");
        Objects.requireNonNull(document.getFileName(), "'document.fileName' is required");
        Objects.requireNonNull(document.getContentType(), "'document.contentType' is required");
        if (document.getFileContent().getLength() == 0) {
            throw new IllegalArgumentException("File size must be greater that 0");
        }
    }

    public static void checkValidURL(String url, String message) {
        boolean valid = isValidURL(url);

        if (!valid) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean isValidURL(String url) {
        try {
            if (url == null) {
                return false;
            }

            URI uri = new URL(url.trim()).toURI();
            return uri.getScheme().equals("http") || uri.getScheme().equals("https");
        } catch (Exception e) {
            return false;
        }
    }
}
