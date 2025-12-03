package com.sprint.sb06deokhugamteam01.repository.batch;

import com.sprint.sb06deokhugamteam01.domain.batch.BatchUserRating;
import com.sprint.sb06deokhugamteam01.domain.batch.PeriodType;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchUserRatingRepository extends JpaRepository<BatchUserRating, UUID> {

    Optional<BatchUserRating> findByPeriodTypeAndPeriodStartAndPeriodEndAndUser_Id(
            PeriodType periodType,
            LocalDate periodStart,
            LocalDate periodEnd,
            UUID userId
    );
}
