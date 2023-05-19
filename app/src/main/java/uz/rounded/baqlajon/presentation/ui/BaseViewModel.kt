package uz.rounded.baqlajon.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.domain.common.Resource
import uz.rounded.baqlajon.presentation.common.UIListState
import uz.rounded.baqlajon.presentation.common.UIObjectState
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _reload = MutableStateFlow(UIObjectState<String>())
    val reload = _reload.asSharedFlow()

    fun <T> getDataList(
        repositoryCall: suspend () -> Flow<Resource<List<T>>>,
        listState: MutableStateFlow<UIListState<T>>
    ) {
        viewModelScope.launch {
            repositoryCall().onEach {
                when (it) {
                    is Resource.Error -> {
                        listState.value = UIListState(it.message ?: "")
                        Log.d("dddddssddsweewe", "getDataObject: ${it.message ?: ""}")
                        _reload.value = UIObjectState(it.message ?: "Something went wrong")
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
                        _reload.value = UIObjectState(it.message ?: "Something went wrong")
                        Log.d("dddddssddsweewe", "getDataObject: ${it.message ?: ""}")
                        listState.value = UIObjectState(it.message ?: "")
                    }
                    is Resource.Loading -> {
                        listState.value = UIObjectState(isLoading = true)
                    }
                    is Resource.Success -> {
                        listState.value = UIObjectState(data = it.data)
                        Log.d("FKJDJFKS", "getDataObject: success")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}