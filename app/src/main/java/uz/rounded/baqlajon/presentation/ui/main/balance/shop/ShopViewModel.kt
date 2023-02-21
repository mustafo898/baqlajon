package uz.rounded.baqlajon.presentation.ui.main.balance.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.domain.model.main.gift.GetGiftModel
import uz.rounded.baqlajon.domain.repository.MainRepository
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _shopList = MutableStateFlow(UIListState<GetGiftModel>())
    val shopList = _shopList.asStateFlow()

    fun getGiftList() {
        viewModelScope.launch {
            repository.getGift().onEach {
                when (it) {
                    is Resource.Error -> {
                        _shopList.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _shopList.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _shopList.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    private val _buy = MutableStateFlow(UIObjectState<String>())
    val buy = _buy.asStateFlow()

    fun buyGift(id: String) {
        viewModelScope.launch {
            repository.buyGift(id).onEach {
                when (it) {
                    is Resource.Error -> {
                        _buy.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        _buy.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _buy.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }
}