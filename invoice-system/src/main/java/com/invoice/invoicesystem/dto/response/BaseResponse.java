package com.invoice.invoicesystem.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
    private boolean flag;
    private Long total;
    private String message;
    private String code;
    private T result;

    public BaseResponse(boolean flag, String message, T result) {
        this.flag = flag;
        this.message = message;
        this.result = result;
    }
}
