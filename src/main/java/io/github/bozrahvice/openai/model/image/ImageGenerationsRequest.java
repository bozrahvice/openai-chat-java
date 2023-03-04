package io.github.bozrahvice.openai.model.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageGenerationsRequest implements Serializable {

    private static final long serialVersionUID = 7364920932740056727L;
    /**
     * A text description of the desired image(s). The maximum length is 1000 characters.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("prompt")
    private String prompt;

    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("n")
    private Integer n;

    /**
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("size")
    private String size;

    /**
     * Defaults to url
     * The format in which the generated images are returned. Must be one of or .url b64_json
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("response_format")
    private String returnPrompt;

    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. Learn more
     * @link https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user")
    private String user;

}