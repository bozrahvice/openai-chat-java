package io.github.bozrahvice.openai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bozrahvice.openai.core.OpenaiEnvironment;
import io.github.bozrahvice.openai.core.OpenaiHttpClient;
import io.github.bozrahvice.openai.model.OpenaiResponse;
import io.github.bozrahvice.openai.model.chat.ChatCompletionsResponse;
import io.github.bozrahvice.openai.model.image.ImageGenerationsRequest;
import io.github.bozrahvice.openai.model.image.ImageGenerationsResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class ImageGenerationsCreate {

    private final static String path = "/v1/images/generations";


    public static OpenaiResponse<ImageGenerationsResponse> imageGenerations(ImageGenerationsRequest imageGenerationsRequest, OpenaiEnvironment environment) {
        OpenaiHttpClient openaiHttpClient = new OpenaiHttpClient(environment);
        OkHttpClient okHttpClient = openaiHttpClient.okHttpClientBuilder();
        String url = environment.baseUrl() + path;
        ObjectMapper objectMapper = new ObjectMapper();
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String requestString;
        try {
            requestString = objectMapper.writeValueAsString(imageGenerationsRequest);
        } catch (JsonProcessingException e) {
            log.error("ImageGenerations requestBody to string error", e);
            throw new RuntimeException(e);
        }
        log.info("ImageGenerations:[{}],requestString: {}", url, requestString);
        RequestBody requestBody = RequestBody.create(requestString, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int code = response.code();
            String message = response.message();
            String body = response.body().string();
            log.info("ImageGenerations response code: {}, message: {}, body: {}", code, message, body);
            ImageGenerationsResponse imageGenerationsResponse = objectMapper.readValue(body, ImageGenerationsResponse.class);
            OpenaiResponse<ImageGenerationsResponse> openaiResponse = OpenaiResponse.<ImageGenerationsResponse>builder()
                    .code(code)
                    .message(message)
                    .data(imageGenerationsResponse)
                    .build();
            log.info("ImageGenerations response: {}", openaiResponse);
            return openaiResponse;
        } catch (Exception e) {
            log.error("ImageGenerations Request failed exception: " + e);
            throw new RuntimeException(e);
        }
    }
}