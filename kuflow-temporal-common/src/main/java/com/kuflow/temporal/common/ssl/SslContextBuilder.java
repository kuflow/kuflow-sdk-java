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

package com.kuflow.temporal.common.ssl;

import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.temporal.serviceclient.SimpleSslContextBuilder;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public final class SslContextBuilder {

    private String ca;
    private String caData;
    private String cert;
    private String certData;
    private String key;
    private String keyData;

    private SslContextBuilder() {}

    public static SslContextBuilder builder() {
        return new SslContextBuilder();
    }

    public SslContextBuilder withCa(String ca) {
        this.ca = ca;
        return this;
    }

    public SslContextBuilder withCaData(String caData) {
        this.caData = caData;
        return this;
    }

    public SslContextBuilder withCert(String cert) {
        this.cert = cert;
        return this;
    }

    public SslContextBuilder withCertData(String certData) {
        this.certData = certData;
        return this;
    }

    public SslContextBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public SslContextBuilder withKeyData(String keyData) {
        this.keyData = keyData;
        return this;
    }

    public SslContext build() {
        if (this.isBlank(this.cert) && this.isBlank(this.certData)) {
            return null;
        }

        if (this.isNotBlank(this.cert) && (this.isBlank(this.key) || this.isBlank(this.ca))) {
            throw new KuFlowTemporalException("key and ca are required");
        }

        if (this.isNotBlank(this.certData) && (this.isBlank(this.keyData) || this.isBlank(this.caData))) {
            throw new KuFlowTemporalException("keyData or caData are required");
        }

        try (
            InputStream certInputStream = this.openInputStream(this.cert, this.certData);
            InputStream keyInputStream = this.openInputStream(this.key, this.keyData);
            InputStream caInputStream = this.openInputStream(this.ca, this.caData)
        ) {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(caInputStream);
            trustStore.setCertificateEntry("temporal-ca", certificate);

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            TrustManager trustManager = trustManagerFactory.getTrustManagers()[0];

            return SimpleSslContextBuilder.forPKCS8(certInputStream, keyInputStream).setTrustManager(trustManager).build();
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new KuFlowTemporalException("Unable to configure mTLS", e);
        }
    }

    private InputStream openInputStream(String file, String pem) {
        if (this.isNotBlank(file)) {
            return this.openInputStreamFromFile(file);
        }

        return this.openInputStreamFromPem(pem);
    }

    private InputStream openInputStreamFromFile(String file) {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new KuFlowTemporalException(String.format("Unable to load %s", file));
        }
    }

    private InputStream openInputStreamFromPem(String pem) {
        return new ByteArrayInputStream(pem.getBytes(StandardCharsets.UTF_8));
    }

    private boolean isBlank(final CharSequence cs) {
        final int strLen = this.length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotBlank(final CharSequence cs) {
        return !this.isBlank(cs);
    }

    private int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
