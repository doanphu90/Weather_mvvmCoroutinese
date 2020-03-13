package com.kotlin.spweather_app


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kotlin.kotlin_rxjava_coroutines_mvvm.repository.Repository
import com.kotlin.sp_weather_app.viewmodel.MainViewModel
import com.kotlin.weatherappcoroutines.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.coroutines.CoroutineContext


@RunWith(JUnit4::class)
class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var mContextScope: TestCoroutineScope
    private lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        mContextScope = TestCoroutineScope()
        viewModel = mock(MainViewModel::class.java)
    }

    @Test
    fun fetchResultSearchCitySuccess() {
        mContextScope.launch {
            verify(viewModel.getResultSearchFromServer("sin"))
        }
    }
}
