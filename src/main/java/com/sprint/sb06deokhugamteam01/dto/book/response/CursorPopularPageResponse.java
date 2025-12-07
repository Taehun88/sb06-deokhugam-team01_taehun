package com.sprint.sb06deokhugamteam01.dto.book.response;

import com.sprint.sb06deokhugamteam01.dto.book.PopularBookDto;

import java.util.List;

public record CursorPopularPageResponse(
        List<PopularBookDto> content,
        String nextCursor,
        String nextAfter,
        int size,
        int totalElements,
        boolean hasNext
) {
}
