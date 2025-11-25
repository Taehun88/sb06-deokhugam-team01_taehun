package com.sprint.sb06deokhugamteam01.dto.book.request;

import com.sprint.sb06deokhugamteam01.domain.Book;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookUpdateRequest(
    String title,
    String author,
    String description,
    String publisher,
    LocalDate publishedDate
) {

    public static Book fromDto(BookUpdateRequest request) {

        return Book.builder()
                .title(request.title())
                .author(request.author())
                .description(request.description())
                .publisher(request.publisher())
                .publishedDate(request.publishedDate())
                .build();

    }

}
