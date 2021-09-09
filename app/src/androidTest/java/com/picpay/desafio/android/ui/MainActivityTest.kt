package com.picpay.desafio.android.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.fragment.ListaUserFragment
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.greaterThan
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest


class MainActivityTest : KoinTest {

    private var server = MockWebServer()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    @Throws(java.lang.Exception::class)
    fun setUp() {
        this.server = MockWebServer()
        this.server.start(serverPort)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        this.server.shutdown()
    }

    @Test
    fun shouldDisplayTitle() {
        launchFragmentInContainer<ListaUserFragment>()

        val expectedTitle = context.getString(R.string.title)
        onView(withText(expectedTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayListItem() {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> successResponse
                    else -> errorResponse
                }
            }
        }

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        launchFragmentInContainer<ListaUserFragment>().onFragment { fragment ->
            navController.setGraph(R.navigation.nav_grahp)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.recyclerView)).check(RecyclerViewItemCountAssertion(50))
        onView(withId(R.id.recyclerView)).check(RecyclerViewItemCountAssertion(greaterThan(10)))
    }

    companion object {
        private const val serverPort = 8080

        private val successResponse by lazy {
            val body =
                "[{\"id\":1001,\"name\":\"Eduardo Santos\",\"img\":\"https://randomuser.me/api/portraits/men/9.jpg\",\"username\":\"@eduardo.santos\"}]"

            MockResponse()
                .setResponseCode(200)
                .setBody(body)
        }

        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }
}