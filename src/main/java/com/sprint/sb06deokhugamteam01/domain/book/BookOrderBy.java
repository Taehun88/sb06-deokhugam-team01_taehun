package com.sprint.sb06deokhugamteam01.domain.book;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookOrderBy {
    TITLE("title"),
    PUBLISHED_DATE("publishedDate"),
    RATING("rating"),
    REVIEW_COUNT("reviewCount");

    private final String fieldName;

}
