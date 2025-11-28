package com.sprint.sb06deokhugamteam01.service.comment;

import com.sprint.sb06deokhugamteam01.dto.comment.CommentCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.comment.CommentDto;
import com.sprint.sb06deokhugamteam01.dto.comment.CommentUpdateRequest;

import java.util.UUID;

public interface CommentService {
    CommentDto createComment(CommentCreateRequest commentCreateRequest);

    CommentDto updateComment(UUID commentId, UUID userId, CommentUpdateRequest commentUpdateRequest);

    void deleteComment(UUID commentId, UUID userId);

    void hardDeleteComment(UUID commentId, UUID userId);
}
