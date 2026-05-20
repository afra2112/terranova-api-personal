package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.user.domain.model.SellerSummary;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserId(UUID userId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByIdentification(String identification);

    boolean existsByEmailOrIdentification(String email, String identification);

    @Query("""
        SELECT new com.terranova.api.v1.user.domain.model.SellerSummary(
                u.userId,
                CONCAT(u.names, ' ', u.lastName), 
                u.email,
                u.phoneNumber,
                u.profilePicture,
                u.userScore
            )
        FROM UserEntity u
        WHERE u.userId IN :ids
    """)
    List<SellerSummary> findSellerSummaryByIds(@Param("ids") List<UUID> ids);
}
