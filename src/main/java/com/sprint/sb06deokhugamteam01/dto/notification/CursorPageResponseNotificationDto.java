package com.sprint.sb06deokhugamteam01.dto.notification;

import com.sprint.sb06deokhugamteam01.domain.Notification;
import java.time.LocalDateTime;
import java.util.UUID;

public record CursorPageResponseNotificationDto(
    UUID id,
    UUID userId,
    UUID reviewId,
    String reviewTitle,
    String content,
    boolean confirmed,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

    public static CursorPageResponseNotificationDto fromEntity(Notification notification) {
        return new CursorPageResponseNotificationDto(
            notification.getId(),
            notification.getUser().getId(),
            notification.getReview() != null ? notification.getReview().getId() : null,
            notification.getReview() != null ? notification.getReview().getBook().getTitle() : null,
            notification.getContent(),
            notification.isConfirmed(),
            notification.getCreatedAt(),
            notification.getUpdatedAt()
        );
    }
}
