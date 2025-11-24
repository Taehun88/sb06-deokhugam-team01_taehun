package com.sprint.sb06deokhugamteam01.service.comment;

import com.sprint.sb06deokhugamteam01.dto.CommentCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.CommentDto;
import com.sprint.sb06deokhugamteam01.dto.CommentUpdateRequest;

public interface CommentService {
    CommentDto createComment(CommentCreateRequest commentCreateRequest);

    CommentDto updateComment(CommentUpdateRequest commentUpdateRequest);
}
