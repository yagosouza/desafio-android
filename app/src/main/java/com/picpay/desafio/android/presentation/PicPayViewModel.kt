package com.picpay.desafio.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.domain.exception.NoCachedUsersException
import com.picpay.desafio.android.presentation.model.UserUiModel
import com.picpay.desafio.android.presentation.model.toUiModel
import kotlinx.coroutines.launch

class PicPayViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<List<UserUiModel>>()
    val users = _users as LiveData<List<UserUiModel>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _showGenericError = MutableLiveData<Boolean>()
    val showGenericError = _showGenericError as LiveData<Boolean>

    private val _showNoCachedUsersError = MutableLiveData<Boolean>()
    val showNoCachedUsersError = _showNoCachedUsersError as LiveData<Boolean>

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            showLoading(true)

            try {
                val userList = getUsersUseCase()

                _users.value = userList.map { user ->
                    user.toUiModel()
                }
            } catch (e: NoCachedUsersException) {
                _showNoCachedUsersError.value = true
            } catch (e: Exception) {
                _showGenericError.value = true
            }

            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}