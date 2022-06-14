package com.picpay.desafio.android.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.domain.exception.NoCachedUsersException
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetUsersTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var repositoryImpl: PicPayRepositoryImpl

    @Mock
    private lateinit var picPayService: PicPayService

    @Mock
    private lateinit var userDao: UserDao

    private val user = User(
        img = "imgTest",
        name = "nameTest",
        id = 1,
        username = "usernameTest"
    )

    private val userResponseList = listOf(user, user, user)

    @Before
    fun init() {
        repositoryImpl = PicPayRepositoryImpl(picPayService, userDao)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `when REQUEST return list with SUCCESS`() =
        dispatcher.runBlockingTest {
            `when`(repositoryImpl.getUsers()).thenReturn(
                userResponseList
            )
            `when`(repositoryImpl.getLocalUsers()).thenReturn(
                userResponseList
            )

            val result = repositoryImpl.getUsers()

            Assert.assertEquals(result.size, userResponseList.size)
        }

    @Test
    fun `when REQUEST return EXCEPTION`() =
        dispatcher.runBlockingTest {
            `when`(repositoryImpl.getUsers()).thenReturn(
                throw NoCachedUsersException()
            )

            val result = repositoryImpl.getUsers()

            Assert.assertEquals(result.size, 0)
        }

}