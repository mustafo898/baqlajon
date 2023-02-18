package uz.rounded.baqlajon.data.mapper

import uz.rounded.baqlajon.data.remote.dto.main.gift.GetGiftDto
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel

//fun UserProfileDto.toModel(): UserProfileModel {
//    return UserProfileModel(
//        _id,
//        code.toModel(),
//        coin.toModel(),
//        createdAt,
//        firstName,
//        image,
//        lastName,
//        password,
//        phoneNumber
//    )
//}
//
//fun CoinDto.toModel(): CoinModel {
//    return CoinModel(_id, allCoin, friendsPaymet, friendsRegister, myPayment, myRegister)
//}
//
//fun CodeDto.toModel(): CodeModel {
//    return CodeModel(_id, code, createdAt, userId)
//}

fun GetGiftDto.toModel(): GetGiftModel {
    return GetGiftModel(_id, coin, createdAt, image, name, status)
}