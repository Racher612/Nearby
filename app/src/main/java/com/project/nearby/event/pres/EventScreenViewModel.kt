package com.project.nearby.event.pres

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.nearby.event.data.RetrofitClient
import com.project.nearby.retrofit.models.concert.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventScreenViewModel @Inject constructor() : ViewModel() {
    private val _event = mutableStateOf<Event?>(null)
    val event : State<Event?> = _event

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> = _loading

    fun loadEvent(id : String){
        _loading.value = true
        viewModelScope.launch {
            _event.value = RetrofitClient.getConcertById(id).body()?._embedded?.events?.first()
            _loading.value = false
        }
    }
}