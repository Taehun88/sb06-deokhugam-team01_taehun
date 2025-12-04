package com.sprint.sb06deokhugamteam01.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.sb06deokhugamteam01.dto.comment.CommentDto;
import com.sprint.sb06deokhugamteam01.dto.comment.request.CommentCreateRequest;
import com.sprint.sb06deokhugamteam01.service.comment.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private CommentService commentService;
    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @Test
    @DisplayName("댓글 등록 성공")
    void createComment_Success() throws Exception {
        // given
        UUID userId = UUID.randomUUID();
        UUID reviewId = UUID.randomUUID();
        String content = "댓글";

        CommentCreateRequest request = new CommentCreateRequest(reviewId, userId, content);

        CommentDto response = CommentDto.builder().id(UUID.randomUUID())
                .reviewId(reviewId).userId(userId).userNickname("유저").content(content)
                .createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();

        given(commentService.createComment(any(CommentCreateRequest.class))).willReturn(response);

        // when & then
        mockMvc.perform(
                        post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.userNickname").value("유저"));
    }
    @Test
    @DisplayName("리뷰 아이디 validation 실패로 댓글 등록 실패")
    void createComment_InvalidReviewId_Fail() throws Exception {
        // given
        UUID userId = UUID.randomUUID();
        String content = "댓글";

        CommentCreateRequest invalidRequest = new CommentCreateRequest(null, userId, content);

        // when & then
        mockMvc.perform(
                        post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest))
                )
                .andExpect(status().isBadRequest());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "    "})
    @DisplayName("댓글 내용 validation 실패로 댓글 등록 실패")
    void createComment_InvalidContent_Fail(String content) throws Exception {
        // given
        UUID userId = UUID.randomUUID();
        UUID reviewId = UUID.randomUUID();

        CommentCreateRequest request = new CommentCreateRequest(reviewId, userId, content);

        // when & then
        mockMvc.perform(
                        post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }
}
