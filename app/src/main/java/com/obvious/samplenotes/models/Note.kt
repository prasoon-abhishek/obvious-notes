package com.obvious.samplenotes.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Prasoon on 2020-02-10.
 */

@Entity(tableName = "note")
data class Note constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val time: String
)