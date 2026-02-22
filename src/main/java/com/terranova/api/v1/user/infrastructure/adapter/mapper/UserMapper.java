package com.terranova.api.v1.user.infrastructure.adapter.mapper;

import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.enums.RoleEnum;
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
                .roles(
                        user.roles().stream().map(RoleEnum::valueOf).toList()
                )
                .userScore(user.userScore())
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
                entity.getRoles().stream().map(Enum::name).toList(),
                entity.getUserScore()
        );
    }
}
