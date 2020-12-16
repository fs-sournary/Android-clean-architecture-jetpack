package com.andrdoidlifelang.presentation.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.andrdoidlifelang.domain.repository.UseCaseResult
import com.andrdoidlifelang.domain.usecase.GetMovieVideoUseCase
import com.andrdoidlifelang.domain.usecase.GetPopularMovieUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.andrdoidlifelang.presentation.model.VideoUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    // Fixme: change page number later
    val popularMovies = getPopularMovieUseCase(1).asLiveData()

    private val videoIdChannel = ConflatedBroadcastChannel<Int>()
    val videoResult: LiveData<UseCaseResult<List<VideoUi>>> = videoIdChannel.asFlow()
        .flatMapLatest { videoId ->
            getMovieVideoUseCase(videoId).map { result -> result.map() }
        }.asLiveData()

    fun setMovieVideoId(movieId: Int) {
        videoIdChannel.offer(movieId)
    }
}
