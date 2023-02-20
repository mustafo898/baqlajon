package uz.rounded.baqlajon.data.mapper

import uz.rounded.baqlajon.data.remote.dto.UserDto
import uz.rounded.baqlajon.domain.model.main.course.UserModel
import uz.rounded.baqlajon.data.remote.dto.*
import uz.rounded.baqlajon.domain.model.*

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

fun UserResponseDto.toModel(): UserResponseModel {
    return UserResponseModel(data = data.toModel(), token = token)
}

fun DataDto.toModel(): DataModel {
    return DataModel(
        _id = _id,
        code = code.toModel(),
        coin = coin.toModel(),
        createdAt = createdAt,
        firstName = firstName,
        image = image,
        lastName = lastName,
        password = password,
        phoneNumber = phoneNumber
    )
}

fun CodeDto.toModel(): CodeModel {
    return CodeModel(_id = _id, code = code, createdAt = createdAt, userId = userId)
}

fun CoinDto.toModel(): CoinModel {
    return CoinModel(
        _id = _id,
        allCoin = allCoin,
        friendsPaymet = friendsPaymet,
        friendsRegister = friendsRegister,
        myPayment = myPayment,
        myRegister = myRegister
    )
}
