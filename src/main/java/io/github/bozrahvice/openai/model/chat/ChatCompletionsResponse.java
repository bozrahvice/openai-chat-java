package io.github.bozrahvice.openai.model.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.bozrahvice.openai.model.Choices;
import io.github.bozrahvice.openai.model.Usage;
import io.github.bozrahvice.openai.model.errorMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ChatCompletionsResponse implements Serializable {

    private static final long serialVersionUID = 1800244894830647127L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private String created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("choices")
    private List<Choices> choices;

    @JsonProperty("session")
    private String session;

    @JsonProperty("usage")
    private Usage usage;

    @JsonProperty("error")
    private errorMessage errorMessage;

}