package com.obvious.samplenotes.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.obvious.samplenotes.models.Note


@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
}

private lateinit var INSTANCE: NotesDatabase

/**
 * Instantiate a database from a context.
 */
fun getDatabase(context: Context): NotesDatabase {
    synchronized(NotesDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
