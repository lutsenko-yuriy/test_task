package com.yurich.test_task.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurich.test_task.R
import com.yurich.test_task.data.repository.Album
import com.yurich.test_task.viewmodels.AlbumsViewState
import com.yurich.test_task.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()

    private val adapter = AlbumsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.albumsStateLiveData.observe(this, Observer { viewState ->
            processViewState(viewState)
        })

        refresher.setOnRefreshListener {
            viewModel.refreshAlbumsData()
        }

        albums_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, VERTICAL))
        }

        viewModel.refreshAlbumsData()
    }

    private fun processViewState(viewState: AlbumsViewState) {
        refresher.isRefreshing = false
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
            AlbumsViewState.Error -> setContainersVisibility(errorWarningVisibility = View.VISIBLE)
        }
    }

    private fun updateAlbumsList(albums: List<Album>) {
        adapter.updateAlbumsList(albums)
    }

    private fun setContainersVisibility(
        errorWarningVisibility: Int = View.GONE,
        nonActualDataWarningVisibility: Int = View.GONE,
        albumsListVisibility: Int = View.GONE
    ) {
        error_warning.visibility = errorWarningVisibility
        non_actual_data_warning.visibility = nonActualDataWarningVisibility
        albums_list.visibility = albumsListVisibility
    }
}
