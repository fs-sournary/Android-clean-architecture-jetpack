package com.andrdoidlifelang.domain.model

sealed class MainGenreType {

    object NowPlaying : MainGenreType()

    object Popular : MainGenreType()

    object TopRate : MainGenreType()

    object UpComing : MainGenreType()
}
