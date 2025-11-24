package com.sprint.sb06deokhugamteam01.service.review;

import com.sprint.sb06deokhugamteam01.dto.review.ReviewCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewLikeDto;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewOperationRequest;
import org.springframework.data.domain.Slice;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public ReviewDto createReview(ReviewCreateRequest request) {
        return null;
    }

    @Override
    public ReviewDto getReviewById(ReviewOperationRequest request) {
        return null;
    }

    @Override
    public Slice<ReviewDto> getReviews() {
        return null;
    }

    @Override
    public Slice<ReviewDto> getPopularReviews() {
        return null;
    }

    @Override
    public ReviewDto updateReview(ReviewOperationRequest request) {
        return null;
    }

    @Override
    public void deleteReview(ReviewOperationRequest request) {

    }

    @Override
    public void hardDeleteReview(ReviewOperationRequest request) {

    }

    @Override
    public ReviewLikeDto likeReview(ReviewOperationRequest request) {
        return null;
    }
}
