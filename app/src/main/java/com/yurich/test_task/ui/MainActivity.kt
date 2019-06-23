package com.yurich.test_task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yurich.test_task.R
import com.yurich.test_task.viewmodels.AlbumsViewState
import com.yurich.test_task.viewmodels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.albumsStateLiveData.observe(this) { viewState ->
            processViewState(viewState)
        }

        if (savedInstanceState == null) {
            viewModel.refreshAlbumsData()
        } else {
            viewModel.loadAlbumsData()
        }
    }

    private fun processViewState(viewState: AlbumsViewState) {
        when (viewState) {
            is AlbumsViewState.ActualAlbums -> TODO()
            is AlbumsViewState.NonActualAlbums -> TODO()
            AlbumsViewState.Loading -> TODO()
            AlbumsViewState.Error -> TODO()
        }
    }
}
