package com.sprint.sb06deokhugamteam01.repository.user;

import com.sprint.sb06deokhugamteam01.domain.User;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepositoryCustom {

    List<User> findPowerUsers(
        String period,
        String direction,
        String cursor,
        LocalDateTime after,
        Integer limit
    );

}
