package io.github.bozrahvice.openai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class errorMessage {

    @JsonProperty("message")
    private String message;

    @JsonProperty("type")
    private String type;

    @JsonProperty("param")
    private String param;

    @JsonProperty("code")
    private String code;
}