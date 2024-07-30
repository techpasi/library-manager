package com.pasindu.librarymanager.dto;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class ApiResponseDTO {
    private int code;
    private String message;
    private Optional<?> data;
    private List<?> dataList;

    public ApiResponseDTO(int code, String message, Optional<?> data, List<?> dataList) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.dataList = dataList;
    }
}
