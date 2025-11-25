package com.sprint.sb06deokhugamteam01.dto.book.request;

import com.sprint.sb06deokhugamteam01.domain.Book;

import java.time.LocalDate;

public record BookCreateRequest(
        String title,
        String author,
        String description,
        String publisher,
        LocalDate publishedDate,
        String isbn
) {

    public static Book fromDto(BookCreateRequest request) {

        return Book.builder()
                .title(request.title())
                .author(request.author())
                .description(request.description())
                .publisher(request.publisher())
                .publishedDate(request.publishedDate())
                .isbn(request.isbn())
                .build();

    }

}
