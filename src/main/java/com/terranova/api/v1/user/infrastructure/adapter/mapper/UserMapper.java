package com.terranova.api.v1.user.infrastructure.adapter.mapper;

import com.terranova.api.v1.auth.infrastructure.adapter.out.mysql.entity.RefreshTokenEntity;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.RoleEntity;
import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user){
        return UserEntity.builder()
                .userId(user.userId())
                .identification(user.identification())
                .names(user.names())
                .lastName(user.lastName())
                .email(user.email())
                .password(user.password())
                .phoneNumber(user.phoneNumber())
                .birthday(user.birthday())
                .registerDate(user.registerDate())
                .profilePicture(user.profilePicture())
                .roleEntities(
                        user.rolesIds().stream()
                                .map(roleId -> {
                                    RoleEntity roleEntity = new RoleEntity();
                                    roleEntity.setRoleId(roleId);
                                    return roleEntity;
                                }).toList()
                )
                .userScore(user.userScore())
//                .refreshTokenEntities(
//                        user.refreshTokenIds().stream()
//                                .map(refreshTokenId -> {
//                                    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
//                                    refreshTokenEntity.setRefreshTokenId(refreshTokenId);
//                                    return refreshTokenEntity;
//                                }).toList()
//                )
                .build();
    }

    public User toDomain(UserEntity entity){
        return new User(
                entity.getUserId(),
                entity.getIdentification(),
                entity.getNames(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getPhoneNumber(),
                entity.getBirthday(),
                entity.getRegisterDate(),
                entity.getProfilePicture(),
                entity.getRoleEntities().stream().map(RoleEntity::getRoleId).toList(),
                entity.getUserScore()
                //entity.getRefreshTokenEntities().stream().map(RefreshTokenEntity::getRefreshTokenId).toList()
        );
    }
}
