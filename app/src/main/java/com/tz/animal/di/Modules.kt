package com.tz.animal.di

import com.tz.animal.dataFlow.DataManager
import com.tz.animal.dataFlow.network.ApiService
import com.tz.animal.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelsModule: Module = module {
    viewModel { MainViewModel(get()) }
}
val apiModule: Module = module {
    single { ApiService.getApi() }
}

private val dataManager: Module = module {
    single { DataManager(get()) }
}

val DI_MODULES = listOf(viewModelsModule, apiModule, dataManager)


