package uz.rounded.baqlajon.data.mapper

import uz.rounded.baqlajon.data.remote.dto.UserDto
import uz.rounded.baqlajon.domain.model.UserModel

fun UserDto.toModel(): UserModel {
    return UserModel(
        _id = _id,
        createdAt = createdAt,
        firstName = firstName,
        image = image,
        lastName = lastName,
        password = password,
        phoneNumber = phoneNumber,
        token = token
    )
}
