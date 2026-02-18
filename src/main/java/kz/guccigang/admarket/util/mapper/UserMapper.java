package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.user.UserCreateRequest;
import kz.guccigang.admarket.dto.user.UserResponse;
import kz.guccigang.admarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserCreateRequest, UserResponse> {

    @Override
    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserCreateRequest request);
}