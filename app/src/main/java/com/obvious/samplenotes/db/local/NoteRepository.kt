package com.obvious.samplenotes.db.local

import android.content.Context
import android.util.Log
import com.obvious.samplenotes.models.Note
import javax.inject.Inject

/**
 * Created by Prasoon on 2020-02-11.
 */
class NoteRepository @Inject constructor(context: Context) {
    private var database: NotesDatabase = getDatabase(context)
    var dao: NotesDao = database.notesDao

    suspend fun saveNote(note: Note) {
        val insertId = dao.insertNote(note)
        Log.d("insertId : ", insertId.toString())
    }

    suspend fun fetchNote(): List<Note?> {
        return dao.getNotes()
    }

}