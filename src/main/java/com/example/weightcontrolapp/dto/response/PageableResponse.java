package com.example.weightcontrolapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageableResponse<T> {

    private T content;

    private int page;

    private int perPage;

    private long totalElements;

    private int totalPages;
}
