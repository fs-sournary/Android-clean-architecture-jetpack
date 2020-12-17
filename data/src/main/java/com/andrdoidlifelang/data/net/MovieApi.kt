package com.andrdoidlifelang.data.net

import com.andrdoidlifelang.data.model.GenreResponseEntity
import com.andrdoidlifelang.data.model.MovieEntity
import com.andrdoidlifelang.data.model.MovieResponseEntity
import com.andrdoidlifelang.data.model.VideoResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): MovieResponseEntity

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): MovieResponseEntity

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MovieResponseEntity

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): MovieResponseEntity

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): MovieResponseEntity

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponseEntity

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int): MovieEntity

    @GET("movie/{movie_id}/similar")
    suspend fun getRelatedMovies(
        @Path("movie_id") id: Int,
        @Query("page") page: Int
    ): MovieResponseEntity

    @GET("movie/{movie_id}/videos")
    suspend fun getVideos(@Path("movie_id") id: Int): VideoResponseEntity
}
