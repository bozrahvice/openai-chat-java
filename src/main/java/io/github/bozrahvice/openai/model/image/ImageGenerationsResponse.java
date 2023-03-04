package io.github.bozrahvice.openai.model.image;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.bozrahvice.openai.model.errorMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImageGenerationsResponse implements Serializable {

    private static final long serialVersionUID = 2440037914080919050L;

    @JsonProperty("created")
    private String created;

    @JsonProperty("data")
    private List<ImageGenerationsData> data;

    @Data
    public static class ImageGenerationsData{

        @JsonProperty("url")
        private String url;

        @JsonProperty("b64_json")
        private String b64Json;
    }

    @JsonProperty("error")
    private errorMessage errorMessage;
}