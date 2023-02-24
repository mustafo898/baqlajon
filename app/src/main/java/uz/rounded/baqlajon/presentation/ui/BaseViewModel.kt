package uz.rounded.baqlajon.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected fun launchScope(
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context, start, block)
    }

    fun <T> getDataList(
        repositoryCall: suspend () -> Flow<Resource<List<T>>>,
        listState: MutableStateFlow<UIListState<T>>
    ) {
        viewModelScope.launch {
            repositoryCall().onEach {
                when (it) {
                    is Resource.Error -> {
                        listState.value = UIListState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        listState.value = UIListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        listState.value = UIListState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

    fun <T> getDataObject(
        repositoryCall: suspend () -> Flow<Resource<T>>,
        listState: MutableStateFlow<UIObjectState<T>>
    ) {
        viewModelScope.launch {
            repositoryCall().onEach {
                when (it) {
                    is Resource.Error -> {
                        listState.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        listState.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        listState.value = UIObjectState(data = it.data)
                    }
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
        }
    }

}