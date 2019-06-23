package com.yurich.test_task.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurich.test_task.data.repository.Album
import com.yurich.test_task.data.repository.AlbumsDataState
import com.yurich.test_task.data.repository.AlbumsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default

class MainActivityViewModel(private val albumsRepository: AlbumsRepository) : ViewModel() {

    private val job = SupervisorJob()

    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    val albumsStateLiveData = MutableLiveData<AlbumsViewState>().apply {
        postValue(AlbumsViewState.Loading)
    }

    fun loadAlbumsData() = coroutineScope.launch {
        albumsStateLiveData.postValue(AlbumsViewState.Loading)
        albumsStateLiveData.postValue(processDataState(albumsRepository.loadAlbumsData()))
    }

    fun refreshAlbumsData() = coroutineScope.launch {
        albumsStateLiveData.postValue(AlbumsViewState.Loading)
        albumsStateLiveData.postValue(processDataState(albumsRepository.refreshAlbumsData()))
    }

    private suspend fun processDataState(loadAlbumsData: AlbumsDataState): AlbumsViewState {
        return when (loadAlbumsData) {
            is AlbumsDataState.FromNetwork -> {
                AlbumsViewState.ActualAlbums(loadAlbumsData.albums.sorted())
            }
            is AlbumsDataState.FromDatabase -> {
                AlbumsViewState.NonActualAlbums(loadAlbumsData.albums.sorted())
            }
            AlbumsDataState.FromNowhere -> AlbumsViewState.Error
        }
    }

    private suspend fun List<Album>.sorted(): List<Album> = withContext(Default) {
        this@sorted.sortedBy { it.title }
    }
}