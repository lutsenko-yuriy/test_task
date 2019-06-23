package com.yurich.test_task.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.yurich.test_task.data.repository.Album
import com.yurich.test_task.data.repository.AlbumsDataState
import com.yurich.test_task.data.repository.AlbumsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: AlbumsRepository

    lateinit var viewModel: MainActivityViewModel

    @Mock
    lateinit var observer: Observer<AlbumsViewState>

    private val mainThreadsSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val albums = listOf(
        Album(15, 1, "DEF"),
        Album(1324, 1234, "MOS"),
        Album(123, 14, "ABC"),
        Album(12, 423, "CCC")
    )

    private val sortedAlbums = albums.sortedBy { it.title }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainActivityViewModel(repository)
        viewModel.albumsStateLiveData.observeForever(observer)
        Dispatchers.setMain(mainThreadsSurrogate)
    }

    @After
    fun tearDown() {
        viewModel.albumsStateLiveData.removeObserver(observer)
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshAlbumsData returns network data`() {
        repository.stub {
            onBlocking { refreshAlbumsData() } doReturn AlbumsDataState.FromNetwork(albums)
        }

        runBlocking {
            viewModel.refreshAlbumsData()
            delay(1000)
            verify(observer).onChanged(AlbumsViewState.ActualAlbums(sortedAlbums))
        }

    }

    @Test
    fun `refreshAlbumsData returns database data`() {
        repository.stub {
            onBlocking { refreshAlbumsData() } doReturn AlbumsDataState.FromDatabase(albums)
        }

        runBlocking {
            viewModel.refreshAlbumsData()
            delay(1000)
            verify(observer).onChanged(AlbumsViewState.NonActualAlbums(sortedAlbums))
        }

    }

    @Test
    fun `refreshAlbumsData returns error`() {
        repository.stub {
            onBlocking { refreshAlbumsData() } doReturn AlbumsDataState.FromNowhere
        }

        runBlocking {
            viewModel.refreshAlbumsData()
            delay(1000)
            verify(observer).onChanged(AlbumsViewState.Error)
        }

    }
}