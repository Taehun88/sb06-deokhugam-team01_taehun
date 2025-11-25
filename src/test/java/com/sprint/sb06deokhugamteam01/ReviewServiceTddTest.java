package com.sprint.sb06deokhugamteam01;

import com.sprint.sb06deokhugamteam01.domain.Book;
import com.sprint.sb06deokhugamteam01.domain.Review;
import com.sprint.sb06deokhugamteam01.domain.User;
import com.sprint.sb06deokhugamteam01.dto.review.*;
import com.sprint.sb06deokhugamteam01.repository.BookRepository;
import com.sprint.sb06deokhugamteam01.repository.ReviewRepository;
import com.sprint.sb06deokhugamteam01.repository.UserRepository;
import com.sprint.sb06deokhugamteam01.service.review.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTddTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private final UUID reviewId = UUID.randomUUID();
    private final UUID userId = UUID.randomUUID();
    private final UUID bookId = UUID.randomUUID();

    Review testReview;
    User testUser;
    Book testBook;
    ReviewOperationRequest testReviewOperationRequest;

    @BeforeEach
    void setUp(){

        testUser = User.builder()
                .id(userId)
                .email("testUser@testUser.com")
                .nickname("testUser")
                .password("testUser")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        testBook = Book.builder()
                .id(bookId)
                .title("testBook")
                .author("author")
                .publisher("publisher")
                .publishedDate(LocalDateTime.now())
                .reviewCount(10)
                .rating(4.5)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .build();

        testReview = Review.builder()
                .id(reviewId)
                .user(testUser)
                .book(testBook)
                .rating(4)
                .content("내용")
                .likeCount(0)
                .commentCount(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .build();

        testReviewOperationRequest = ReviewOperationRequest.builder()
                .reviewId(reviewId)
                .userId(userId)
                .build();
    }

    @Test
    @DisplayName("createReview 메서드는 호출 시 Review 객체를 생성한다.")
    void createReview_성공() {

        // given
        ReviewCreateRequest request = ReviewCreateRequest.builder()
                .bookId(bookId)
                .userId(userId)
                .content("테스트내용")
                .rating(5)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(testBook));

        Review mockSavedReview = Review.builder()
                .id(UUID.randomUUID())
                .user(testUser)
                .book(testBook)
                .rating(request.rating())
                .content(request.content())
                .likeCount(1)
                .commentCount(2)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isActive(true)
                .build();

        when(reviewRepository.save(any(Review.class))).thenReturn(mockSavedReview);

        // when
        ReviewDto response = reviewService.createReview(request);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull();
        assertThat(response.bookId()).isEqualTo(bookId);
        assertThat(response.userId()).isEqualTo(userId);
        assertThat(response.content()).isEqualTo("테스트내용");
        assertThat(response.rating()).isEqualTo(5);
    }

    @Test
    @DisplayName("getReview 메서드는 호출 시 ReviewDto를 반환한다.")
    void getReview_성공(){

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when
        ReviewDto response = reviewService.getReview(testReviewOperationRequest);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(reviewId);
        assertThat(response.userId()).isEqualTo(userId);
        assertThat(response.bookTitle()).isEqualTo(testBook.getTitle());
        assertThat(response.content()).isEqualTo("내용");

        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    @DisplayName("getReviews 메서드는 호출 시 CursorPageResponseReviewDto를 반환한다")
    void getReviews_TDD_성공() {

        // Given
        UUID requestUserId = UUID.randomUUID();
        CursorPageReviewRequest request = CursorPageReviewRequest.builder()
                .userId(userId)
                .bookId(bookId)
                .keyword(null)
                .orderBy(CursorPageReviewRequest.SortOrder.createdAt)
                .direction(CursorPageReviewRequest.SortDirection.DESC)
                .cursor(null)
                .after(null)
                .limit(10)
                .requestUserId(requestUserId)
                .build();

        CursorPageResponseReviewDto mockResponse = CursorPageResponseReviewDto.builder()
                .content(Collections.emptyList())
                .nextCursor(null)
                .nextAfter(null)
                .size(0)
                .totalElements(0)
                .hasNext(false)
                .build();

        when(reviewService.getReviews(any(CursorPageReviewRequest.class)))
                .thenReturn(mockResponse); // <- 이 라인은 ServiceImpl의 Mock이 아닌 실제 구현체를 가정할 때 사용됩니다.
        // 현재는 @InjectMocks에 의해 실제 구현체 (로직이 없는)가 주입되므로
        // 반드시 예외 발생 또는 null 반환으로 실패해야 합니다.

        // When
        CursorPageResponseReviewDto response = reviewService.getReviews(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.hasNext()).isFalse();
        assertThat(response.size()).isZero();
    }

    @Test
    @DisplayName("deleteReview 메서드는 호출 시 Review 객체의 isActive를 false로 바꾼다.")
    void updateReview_성공(){

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        ReviewUpdateRequest updateRequest = ReviewUpdateRequest.builder()
                .content("수정")
                .rating(1)
                .build();

        // when
        ReviewDto response = reviewService.updateReview(testReviewOperationRequest, updateRequest);

        // then
        assertThat(testReview.getContent()).isEqualTo(updateRequest.content());
        assertThat(testReview.getRating()).isEqualTo(updateRequest.rating());
        assertThat(response.content()).isEqualTo(updateRequest.content());
        assertThat(response.rating()).isEqualTo(updateRequest.rating());
        verify(reviewRepository, times(1)).save(testReview);
    }

    @Test
    @DisplayName("updateReview 메서드는 rating이 허용 범위를 벗어날 때 IllegalArgumentException을 던진다.")
    void updateReview_실패_Rating_범위_초과() {

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // 유효하지 않은 rating 값 (예: 최대치인 5를 초과한 6)
        ReviewUpdateRequest invalidRequest = ReviewUpdateRequest.builder()
                .content("유효한 내용")
                .rating(6)
                .build();

        // when & then
        assertThatThrownBy(() -> reviewService.updateReview(testReviewOperationRequest, invalidRequest))
                .isInstanceOf(IllegalArgumentException.class);

        verify(reviewRepository, never()).save(any());
    }

    @Test
    @DisplayName("deleteReview 메서드는 호출 시 Review 객체의 isActive를 false로 바꾼다.")
    void deleteReview_성공(){

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when
        reviewService.deleteReview(testReviewOperationRequest);

        // then
        assertThat(testReview.isActive()).isFalse();
        verify(reviewRepository, times(1)).save(testReview);
    }

    @Test
    @DisplayName("deleteReview 메서드는 User를 찾을 수 없을 때 IllegalArgumentException을 던진다.")
    void deleteReview_실패_User_없음() {

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> reviewService.deleteReview(testReviewOperationRequest))
                .isInstanceOf(IllegalArgumentException.class);

        verify(reviewRepository, never()).save(testReview);
    }

    @Test
    @DisplayName("hardDeleteReview 메서드는 호출 시 Review 객체를 삭제한다.")
    void hardDeleteReview_성공() {

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when
        reviewService.hardDeleteReview(testReviewOperationRequest);

        // then
        verify(reviewRepository, times(1)).deleteById(reviewId);
    }

    @Test
    @DisplayName("hardDeleteReview 메서드는 Review를 찾을 수 없을 때 IllegalArgumentException을 던진다.")
    void hardDeleteReview_실패_Review_없음() {
        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when & then
        assertThatThrownBy(() -> reviewService.hardDeleteReview(testReviewOperationRequest))
                .isInstanceOf(IllegalArgumentException.class);

        verify(reviewRepository, never()).deleteById(reviewId);
    }

    @Test
    @DisplayName("likeReview 메서드는 호출 시 Review의 likeCount를 1 증가시키고 ReviewLikeDto를 반환한다.")
    void likeReview_성공() {

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(testReview));
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when
        ReviewLikeDto response = reviewService.likeReview(testReviewOperationRequest);

        // then
        assertThat(response).isNotNull();
        assertThat(response.reviewId()).isEqualTo(reviewId);
        assertThat(response.userId()).isEqualTo(userId);
        assertThat(response.liked()).isTrue();
        assertThat(testReview.getLikeCount()).isEqualTo(1);
        verify(reviewRepository, times(1)).save(testReview);
    }

    @Test
    @DisplayName("likeReview 메서드는 Review를 찾을 수 없을 때 IllegalArgumentException을 던진다.")
    void likeReview_실패_Review_없음() {

        // given
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty()); // 리뷰 없음
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        // when & then
        assertThatThrownBy(() -> reviewService.likeReview(testReviewOperationRequest))
                .isInstanceOf(IllegalArgumentException.class); // TODO 커스텀예외로 대체

        verify(reviewRepository, never()).save(any());
    }
}
