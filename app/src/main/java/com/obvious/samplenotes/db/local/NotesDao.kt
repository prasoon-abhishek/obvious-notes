package com.obvious.samplenotes.db.local

import androidx.room.*
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

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}