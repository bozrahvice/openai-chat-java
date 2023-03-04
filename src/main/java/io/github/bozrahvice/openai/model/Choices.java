package io.github.bozrahvice.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Choices {

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;


}