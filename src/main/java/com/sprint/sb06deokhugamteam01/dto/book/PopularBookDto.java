package com.sprint.sb06deokhugamteam01.dto.book;

import com.sprint.sb06deokhugamteam01.domain.batch.PeriodType;

import java.time.LocalDateTime;

public record PopularBookDto(
        String id,
        String bookId,
        String title,
        String author,
        String thumbnailUrl,
        PeriodType period,
        Long rank,
        Double score,
        Long reviewCount,
        Double rating,
        LocalDateTime createdAt
) {
}
