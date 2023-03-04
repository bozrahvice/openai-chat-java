package io.github.bozrahvice.openai;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bozrahvice.openai.model.chat.ChatCompletionsRequest;
import io.github.bozrahvice.openai.model.chat.ChatCompletionsResponse;
import io.github.bozrahvice.openai.core.OpenaiEnvironment;
import io.github.bozrahvice.openai.core.OpenaiHttpClient;
import io.github.bozrahvice.openai.model.OpenaiResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public class ChatCompletionsCreate {

    private final static String path = "/v1/chat/completions";

    public static OpenaiResponse<ChatCompletionsResponse> chatCompletions(ChatCompletionsRequest chatCompletionsRequest, OpenaiEnvironment environment) {
        OpenaiHttpClient openaiHttpClient = new OpenaiHttpClient(environment);
        OkHttpClient okHttpClient = openaiHttpClient.okHttpClientBuilder();
        String url = environment.baseUrl() + path;
        ObjectMapper objectMapper = new ObjectMapper();
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String requestString;
        try {
            requestString = objectMapper.writeValueAsString(chatCompletionsRequest);
        } catch (JsonProcessingException e) {
            log.error("requestBody to string error", e);
            throw new RuntimeException(e);
        }
        log.info("requestString: {}", requestString);
        RequestBody requestBody = RequestBody.create(requestString, mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            int code = response.code();
            String message = response.message();
            String body = response.body().string();
            log.info("chatCompletions response code: {}, message: {}, body: {}", code, message, body);
            ChatCompletionsResponse chatCompletionsResponse = objectMapper.readValue(body, ChatCompletionsResponse.class);
            OpenaiResponse<ChatCompletionsResponse> openaiResponse = OpenaiResponse.<ChatCompletionsResponse>builder()
                    .code(code)
                    .message(message)
                    .data(chatCompletionsResponse)
                    .build();
            log.info("chatCompletions response: {}", openaiResponse);
            return openaiResponse;
        } catch (Exception e) {
            log.error("Request failed exception: " + e);
            throw new RuntimeException(e);
        }
    }

}