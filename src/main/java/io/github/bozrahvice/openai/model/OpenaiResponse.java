package io.github.bozrahvice.openai.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenaiResponse<T> {

    private int code;

    private String message;

    private T data;
}