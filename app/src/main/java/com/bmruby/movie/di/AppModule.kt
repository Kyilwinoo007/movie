package com.bmruby.movie.di

import com.bmruby.movie.model.remote.ApiClient
import com.bmruby.movie.repository.home.HomeRepository
import com.bmruby.movie.repository.home.HomeRepositoryImpl
import com.bmruby.movie.ui.detail.DetailViewModel
import com.bmruby.movie.ui.home.HomeViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single <HomeRepository> { HomeRepositoryImpl(apiClient = get(), db = get(),
                                                    movieDao = get(), upComingMovieDao = get()) }
    single { ApiClient.apiService()}

    viewModel {
        HomeViewModel(repository = get())
    }
    viewModel {
        DetailViewModel(repository = get())
    }
}