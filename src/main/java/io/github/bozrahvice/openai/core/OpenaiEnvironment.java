package io.github.bozrahvice.openai.core;


public class OpenaiEnvironment implements Environment {

    private String baseUrl;

    private String apiKey;

    private String proxyHost;

    private Integer proxyPort;

    private boolean useProxy;

    public OpenaiEnvironment(String apiKey, String baseUrl, String proxyHost, Integer proxyPort, boolean useProxy) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.useProxy = useProxy;
    }

    public OpenaiEnvironment(String apiKey, String baseUrl) {
        this(apiKey, baseUrl, "", null, false);
    }

    @Override
    public String baseUrl() {
        return baseUrl;
    }

    public String apiKey() {
        return apiKey;
    }


    public String proxyHost() {
        return proxyHost;
    }


    public Integer proxyPort() {
        return proxyPort;
    }

    public boolean useProxy() {
        return useProxy;
    }


    public static class proxy extends OpenaiEnvironment {
        public proxy(String apiKey) {
            super(apiKey, "https://api.openai.com", "127.0.0.1", 7890, true);
        }
    }

    public static class normal extends OpenaiEnvironment {
        public normal(String apiKey) {
            super(apiKey, "https://api.openai.com");
        }
    }
}