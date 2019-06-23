package com.yurich.test_task.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yurich.test_task.R
import com.yurich.test_task.viewmodels.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
