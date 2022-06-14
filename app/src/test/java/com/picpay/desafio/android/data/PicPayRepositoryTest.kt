package com.picpay.desafio.android.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.local.UserEntity
import com.picpay.desafio.android.data.model.UserResponse
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PicPayRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var repositoryImpl: PicPayRepositoryImpl

    @Mock
    private lateinit var picPayService: PicPayService

    @Mock
    private lateinit var userDao: UserDao

    private val userResponse = UserResponse(
        img = "imgTest",
        name = "nameTest",
        id = 1,
        username = "usernameTest"
    )

    private val userResponseList = listOf(userResponse, userResponse, userResponse)

    private val userEntity = UserEntity(
        img = "imgTest",
        name = "nameTest",
        idUser = 1,
        userName = "usernameTest"
    )

    private val userEntityList = listOf(userEntity, userEntity, userEntity)

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

//    @Test
//    fun `when REQUEST returns SUCCESSFULLY expect success`() =
//        dispatcher.runBlockingTest {
//            `when`(picPayService.getUsers().parseResponse()).thenReturn(
//                Output(userResponseList)
//            )
//        }
}