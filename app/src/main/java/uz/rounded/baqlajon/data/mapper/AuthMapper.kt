package uz.rounded.baqlajon.data.mapper

import uz.rounded.baqlajon.data.remote.dto.*
import uz.rounded.baqlajon.domain.model.CodeModel
import uz.rounded.baqlajon.domain.model.CoinModel
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.UserResponseModel
import uz.rounded.baqlajon.domain.model.main.course.UserModel

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
        code = code?.let { it.toModel() },
        coin = coin?.let { it.toModel() },
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
