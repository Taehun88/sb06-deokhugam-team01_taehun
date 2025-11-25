package com.sprint.sb06deokhugamteam01.service.review;

import com.sprint.sb06deokhugamteam01.dto.review.*;

public interface ReviewService {

    ReviewDto createReview(ReviewCreateRequest request);

    ReviewDto getReview(ReviewOperationRequest request);

    CursorPageResponseReviewDto getReviews(CursorPageReviewRequest request);

    CursorPageResponsePopularReviewDto getPopularReviews(CursorPagePopularReviewRequest request);

    ReviewDto updateReview(ReviewOperationRequest request, ReviewUpdateRequest updateRequest);

    void deleteReview(ReviewOperationRequest request);

    void hardDeleteReview(ReviewOperationRequest request);

    ReviewLikeDto likeReview(ReviewOperationRequest request);

}
