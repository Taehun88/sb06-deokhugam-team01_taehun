package com.sprint.sb06deokhugamteam01.dto.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
public record PagingBookRequest(
     String keyword,
     @NotBlank(message = "orderBy는 비어 있을 수 없습니다.")
     OrderBy orderBy,
     @NotBlank(message = "direction는 비어 있을 수 없습니다.")
     SortDirection direction,
     String cursor,
     LocalDateTime after,
     @PositiveOrZero(message = "limit는 0 이상의 정수여야 합니다.")
     Integer limit
) {

    @RequiredArgsConstructor
    public enum OrderBy {
        TITLE("title"),
        PUBLISHED_DATE("publishedDate"),
        RATING("rating"),
        REVIEW_COUNT("reviewCount");

        private final String fieldName;
    }

    public enum SortDirection {
        ASC,
        DESC
    }

}
