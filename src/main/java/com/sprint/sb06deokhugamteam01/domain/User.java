package com.sprint.sb06deokhugamteam01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @Column
    private UUID id;

    private String email;

    private String nickname;
    private String password;

    private LocalDateTime createdAt;
    private boolean isActive;

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void updateProfile(String nickname, String password) {
        if (nickname != null && !nickname.isBlank()) {
            this.nickname = nickname;
        }
        if (password != null && !password.isBlank()) {
            this.password = password;
        }
    }
}
