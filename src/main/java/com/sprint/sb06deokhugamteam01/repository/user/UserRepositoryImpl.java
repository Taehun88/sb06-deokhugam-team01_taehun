package com.sprint.sb06deokhugamteam01.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sprint.sb06deokhugamteam01.domain.QReviewLog;
import com.sprint.sb06deokhugamteam01.domain.QUser;
import com.sprint.sb06deokhugamteam01.domain.User;
import com.sprint.sb06deokhugamteam01.domain.review.QReview;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;



    @Override
    public List<User> findPowerUsers(String period, String direction, String cursor,
        LocalDateTime after, Integer limit) {

        return List.of();
    }
}
