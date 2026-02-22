package kz.guccigang.admarket.util.mapper;

import kz.guccigang.admarket.dto.user.UserCreateRequest;
import kz.guccigang.admarket.dto.user.UserResponse;
import kz.guccigang.admarket.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends Mappable<User, UserCreateRequest, UserResponse> {

    @Override
    @Mapping(target = "password", ignore = true)
    User toEntity(UserCreateRequest request);
}