package com.yurich.test_task.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurich.test_task.R
import com.yurich.test_task.data.repository.Album
import com.yurich.test_task.viewmodels.AlbumsViewState
import com.yurich.test_task.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<MainActivityViewModel>()

    val adapter = AlbumsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.albumsStateLiveData.observe(this) { viewState ->
            processViewState(viewState)
        }

        albums_list.layoutManager = LinearLayoutManager(this)
        albums_list.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.refreshAlbumsData()
        } else {
            viewModel.loadAlbumsData()
        }
    }

    private fun processViewState(viewState: AlbumsViewState) {
        when (viewState) {
            is AlbumsViewState.ActualAlbums -> {
                setContainersVisibility(
                    albumsListVisibility = View.VISIBLE
                )
                updateAlbumsList(viewState.albums)
            }
            is AlbumsViewState.NonActualAlbums -> {
                setContainersVisibility(
                    nonActualDataWarningVisibility = View.VISIBLE,
                    albumsListVisibility = View.VISIBLE
                )
                updateAlbumsList(viewState.albums)
            }
            AlbumsViewState.Loading -> setContainersVisibility(loadingVisibility = View.VISIBLE)
            AlbumsViewState.Error -> setContainersVisibility(errorWarningVisibility = View.VISIBLE)
        }
    }

    private fun updateAlbumsList(albums: List<Album>) {
        adapter.updateAlbumsList(albums)
    }

    private fun setContainersVisibility(
        loadingVisibility: Int = View.GONE,
        errorWarningVisibility: Int = View.GONE,
        nonActualDataWarningVisibility: Int = View.GONE,
        albumsListVisibility: Int = View.GONE
    ) {
        albums_loading.visibility = loadingVisibility
        error_warning.visibility = errorWarningVisibility
        non_actual_data_warning.visibility = nonActualDataWarningVisibility
        albums_list.visibility = albumsListVisibility
    }
}
