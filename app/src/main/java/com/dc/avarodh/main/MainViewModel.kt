package com.dc.avarodh.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(){

    val uiState = mutableStateOf<MainUiState>(MainUiState.NotLoggedIn)

    init {
        /*if(uiState.value is MainUiState.LoggedIn)
            fetchList()*/
    }

    fun fetchList() {
        TODO("Not yet implemented")
    }

}