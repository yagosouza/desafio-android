package com.picpay.desafio.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicPayViewModel(
    private val repository: PicPayRepository
) : ViewModel() {

    val usersLiveData = MutableLiveData<List<User>>()

    fun getUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.Default) {
                repository.getList()
            }

            usersLiveData.value = response
        }
    }
}