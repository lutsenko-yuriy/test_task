package com.yurich.test_task.di

import com.yurich.test_task.viewmodels.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainActivityViewModel(get()) }

}