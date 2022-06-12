package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.GetUsersException
import com.picpay.desafio.android.data.PicPayRepository
import com.picpay.desafio.android.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetUsersTestMockk {

    private val repository = mockk<PicPayRepository>()

    private val getUsers = GetUsers(repository)

    private var user: User = User(
        id = 1,
        img = "testeImg",
        name = "testeName",
        username = "testUsername"
    )

    private var listsOfUsers: List<User> = listOf(user, user, user, user)

    @Test
    fun getUsers_return_list_with_success() = runBlocking {
        //GIVEN
        coEvery { repository.getUsers() } returns listsOfUsers

        //WHEN
        val result = getUsers()

        //THEN
        Assert.assertEquals(result.size, listsOfUsers.size)
    }

    @Test
    fun getUsers_return_exception() = runBlocking {
        //GIVEN
        coEvery { repository.getUsers() } throws GetUsersException()

        //WHEN
        val result = getUsers()

        //THEN
        Assert.assertEquals(result.size, 0)
    }
}