package com.picpay.desafio.android.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.presenter.model.UserUiModel
import com.picpay.desafio.android.presenter.model.toUiModel
import kotlinx.coroutines.launch

class PicPayViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<List<UserUiModel>>()
    val users = _users as LiveData<List<UserUiModel>>

    fun getUsers() {
        viewModelScope.launch {
            val userList = getUsersUseCase()

            _users.value = userList.map { user ->
                user.toUiModel()
            }
        }
    }
}