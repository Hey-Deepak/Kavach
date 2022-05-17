package com.dc.kavach.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dc.kavach.domain.repository.UserRepository
import com.dc.kavach.domain.usecase.BannedAppsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val bannedAppsUsecase: BannedAppsUsecase
): ViewModel(){

    val uiState = mutableStateOf<MainUiState>(MainUiState.NotLoggedIn)

    init {
        getEmailFromPrefsAndFetchData()
    }

    private fun getEmailFromPrefsAndFetchData() {
        userRepository.getLoggedInEmailFromPrefs()?.let {
            uiState.value = MainUiState.LoggedIn(it)
            fetchList()
        }
    }

    fun fetchList() {
        uiState.value = MainUiState.Loading

        viewModelScope.launch {
            bannedAppsUsecase.getBannedApps()
                .onSuccess {
                    uiState.value = MainUiState.FilteredAppList(it)
                }.onFailure {
                    uiState.value = MainUiState.Error(it)
                }
        }
    }

    fun refreshList() {
        viewModelScope.launch {
            bannedAppsUsecase.refreshList()
        }
    }

    fun refreshTimestamp(){
        viewModelScope.launch {
            bannedAppsUsecase.refreshTimestamp()
        }
    }

}