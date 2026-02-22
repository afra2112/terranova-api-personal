package com.terranova.api.v1.user.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.RoleEntity;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(RoleEnum name);
}
