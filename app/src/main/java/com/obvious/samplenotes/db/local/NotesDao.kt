package com.obvious.samplenotes.db.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.obvious.samplenotes.models.Note

/**
 * Created by Prasoon on 2020-02-11.
 */
@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Note): Long

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getNotes(): List<Note>
}