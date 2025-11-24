package com.sprint.sb06deokhugamteam01.service.review;

import com.sprint.sb06deokhugamteam01.dto.review.ReviewCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewLikeDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewOperationRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReviewService {

    ReviewDto createReview(ReviewCreateRequest request);

    ReviewDto getReviewById(ReviewOperationRequest request);

    Slice<ReviewDto> getReviews(); // TODO 페이지네이션 조회 요청 DTO 추가

    Slice<ReviewDto> getPopularReviews(); // TODO 페이지네이션 조회 요청 DTO 추가

    ReviewDto updateReview(ReviewOperationRequest request);

    void deleteReview(ReviewOperationRequest request);

    void hardDeleteReview(ReviewOperationRequest request);

    ReviewLikeDto likeReview(ReviewOperationRequest request);

}
