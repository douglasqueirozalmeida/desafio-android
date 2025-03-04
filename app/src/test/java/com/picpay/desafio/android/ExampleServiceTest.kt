package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.service.PicPayServiceApi
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayServiceApi>()

    private val service = ExampleService(api)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun exampleTest() = runBlockingTest {
        // given
        val call = mock<Call<List<User>?>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(Response.success(null))

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}