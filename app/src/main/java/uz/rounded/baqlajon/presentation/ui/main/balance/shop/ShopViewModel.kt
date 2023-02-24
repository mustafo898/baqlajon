package uz.rounded.baqlajon.presentation.ui.main.balance.shop

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import uz.rounded.baqlajon.presentation.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    private val _shopList = MutableStateFlow(UIListState<GetGiftModel>())
    val shopList = _shopList.asStateFlow()

    fun getGiftList() {
        getDataList({ repository.getGift() }, _shopList)
    }

    private val _buy = MutableStateFlow(UIObjectState<String>())
    val buy = _buy.asStateFlow()

    fun buyGift(id: String) {
        getDataObject({ repository.buyGift(id = id) }, _buy)
    }
}