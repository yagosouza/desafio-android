package com.picpay.desafio.android.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.domain.model.User
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PicPayViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private var getUsersUseCaseMock: GetUsersUseCaseMock? = null

    private var usersListMock: User = User(
        id = 1,
        img = "testeImg",
        name = "testeName",
        username = "testUsername"
    )

    private var listsOfUsersMock: List<User> = listOf(usersListMock, usersListMock, usersListMock)

    @Before
    fun init() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

//    @Test
//    fun `when LISTS OF USERS REQUEST returns SUCCESSFULLY expect livedata list filled`() =
//        dispatcher.runBlockingTest {
//            getUsersUseCaseMock = GetUsersUseCaseMock(listsOfUsersMock)
//            val viewModel = PicPayViewModel(getUsersUseCaseMock!!)
//
//            viewModel.getUsers()
//
//            Assert.assertEquals(listsOfUsersMock, viewModel.users?.value)
//        }
}

class GetUsersUseCaseMock(private val userList: List<User>) : GetUsersUseCase {

    override suspend fun invoke(): List<User> {
        return userList
    }

}