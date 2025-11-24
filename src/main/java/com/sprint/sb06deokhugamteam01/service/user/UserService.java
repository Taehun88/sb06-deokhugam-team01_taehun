package com.sprint.sb06deokhugamteam01.service.user;

import com.sprint.sb06deokhugamteam01.domain.User;
import java.util.UUID;

public interface UserService {

    User registerUser(String email, String nickname, String password);

    User login(String email, String password);

    User getUser(UUID userId);

    User deactivateUser(UUID userId);

    User updateUser(UUID userId, String nickname, String password);

    void deleteUser(UUID userId);
}
