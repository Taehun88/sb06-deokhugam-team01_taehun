package com.sprint.sb06deokhugamteam01.controller;

import com.sprint.sb06deokhugamteam01.dto.comment.CommentCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.comment.CommentDto;
import com.sprint.sb06deokhugamteam01.dto.comment.CommentUpdateRequest;
import com.sprint.sb06deokhugamteam01.service.comment.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentCreateRequest request) {
        log.info("댓글 생성 요청: userId={}, reviewId={}", request.userId(), request.reviewId());
        log.debug("댓글 생성 요청 상세 데이터: {}", request);
        CommentDto createdComment = commentService.createComment(request);
        log.debug("댓글 생성 응답: commentId={}", createdComment.id());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdComment);
    }

    @PatchMapping(path = "/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("commentId") UUID commentId,
                                                    @RequestHeader("Deokhugam-Request-User-ID") UUID userId,
                                                    @Valid @RequestBody CommentUpdateRequest request) {
        log.info("댓글 수정 요청: userId={}, commentId={}", userId, commentId);
        log.debug("댓글 수정 요청 상세 데이터: request={}", request);
        CommentDto updatedComment = commentService.updateComment(commentId, userId, request);
        log.debug("댓글 수정 응답: commentId={}", updatedComment.id());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedComment);
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") UUID commentId,
                                                    @RequestHeader("Deokhugam-Request-User-ID") UUID userId) {
        log.info("댓글 논리 삭제 요청: commentId={}, userId={}", commentId, userId);
        commentService.deleteComment(commentId, userId);
        log.debug("댓글 논리 삭제 응답: commentId={}", commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @DeleteMapping(path = "/{commentId}/hard")
    public ResponseEntity<Void> hardDeleteComment(@PathVariable("commentId") UUID commentId,
                                                        @RequestHeader("Deokhugam-Request-User-ID") UUID userId) {
        log.info("댓글 물리 삭제 요청: commentId={}, userId={}", commentId, userId);
        commentService.hardDeleteComment(commentId, userId);
        log.debug("댓글 물리 삭제 응답: commentId={}", commentId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
