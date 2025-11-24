package com.sprint.sb06deokhugamteam01;

import com.sprint.sb06deokhugamteam01.domain.Book;
import com.sprint.sb06deokhugamteam01.domain.Review;
import com.sprint.sb06deokhugamteam01.domain.User;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewCreateRequest;
import com.sprint.sb06deokhugamteam01.dto.review.ReviewDto;
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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    private final UUID userId = UUID.randomUUID();
    private final UUID bookId = UUID.randomUUID();

    User testUser;
    Book testBook;

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
    }

    @Test
    @DisplayName("createReview 메서드는 호출 시 Review 객체를 생성한다.")
    void createReview_TDD() {

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
        ReviewDto response = reviewService.createReview(request); // createReview()가 항상 null을 반환함.

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull();
        assertThat(response.bookId()).isEqualTo(bookId);
        assertThat(response.userId()).isEqualTo(userId);
        assertThat(response.content()).isEqualTo("테스트내용");
        assertThat(response.rating()).isEqualTo(5);
    }

}
