package com.andrdoidlifelang.data.db

import androidx.room.Dao
import com.andrdoidlifelang.data.model.VideoEntity

@Dao
interface VideoDao : BaseDao<VideoEntity>
