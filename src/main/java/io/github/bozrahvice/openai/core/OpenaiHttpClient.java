package io.github.bozrahvice.openai.core;


import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class OpenaiHttpClient {

    private OpenaiEnvironment openaiEnvironment;

    private SSLSocketFactory sslSocketFactory;

    private String userAgent;

    private Duration connectTimeout;

    private Duration readTimeout;

    public OpenaiHttpClient(OpenaiEnvironment openaiEnvironment) {
        this.openaiEnvironment = openaiEnvironment;
        this.readTimeout = Duration.ofMillis(TimeUnit.SECONDS.toMillis(30));
        this.connectTimeout = readTimeout;

    }

    public OkHttpClient okHttpClientBuilder() {
        OkHttpClient.Builder okHttpClientBuilder;
        if (openaiEnvironment.useProxy()) {
            okHttpClientBuilder = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(openaiEnvironment.proxyHost(), openaiEnvironment.proxyPort())));
        } else {
            okHttpClientBuilder = new OkHttpClient.Builder();
        }
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new X509TrustManager[]{trustManager}, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
        return okHttpClientBuilder
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                .addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + openaiEnvironment.apiKey()).build()))
                .build();
    }


}