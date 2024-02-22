package com.bmruby.movie.di

import android.app.Application
import com.bmruby.movie.MyApplication
import com.bmruby.movie.model.local.MovieDb
import com.bmruby.movie.model.remote.ApiClient
import com.bmruby.movie.repository.home.HomeRepository
import com.bmruby.movie.repository.home.HomeRepositoryImpl
import com.bmruby.movie.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single <HomeRepository> { HomeRepositoryImpl(apiClient = get(), db = get(),
                                                    movieDao = get(), upComingMovieDao = get()) }
    single { ApiClient.apiService()}

    viewModel {
        HomeViewModel(repository = get())
    }
}