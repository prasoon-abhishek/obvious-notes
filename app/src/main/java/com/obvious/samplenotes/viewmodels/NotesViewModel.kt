package com.obvious.samplenotes.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obvious.samplenotes.db.local.NoteRepository
import com.obvious.samplenotes.models.Note
import com.obvious.samplenotes.utils.event
import com.obvious.samplenotes.utils.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Prasoon on 2020-02-10.
 */
@Singleton
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _showError = MutableLiveData<String>()
    val showError = _showError.event()

    private val _showToast = MutableLiveData<String>()
    val showToast = _showToast.event()

    private val _noteDetails = MutableLiveData<Note>()
    val noteDetails = _noteDetails.event()

    private val _navigateToNoteDetails = MutableLiveData<Unit>()
    val navigateToNoteDetails = _navigateToNoteDetails.event()

    private val _navigateToNotes = MutableLiveData<Unit>()
    val navigateToNotes = _navigateToNotes.event()

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList = _notesList
        .map {
            val noteList = arrayListOf<Note>()
            it?.let {
                noteList.addAll(it)
            }
            noteList
        }

    fun saveNote(title: String, content: String) {
        if (title.isEmpty()) {
            _showError.value = "Title Can't Be Empty"
            return
        }
        if (content.isEmpty()) {
            _showError.value = "Content Can't Be Empty"
            return
        }
        val note = Note(
            0,
            title,
            content,
            System.currentTimeMillis().toString()
        )
        viewModelScope.launch {
            repository.saveNote(note)
            _showToast.value = "Note Saved"
            navigateToNotes()
            fetchNotes()
        }
    }

    fun navigateToNotes() {
        _navigateToNotes.value = Unit
    }

    fun fetchNotes() {
        Log.d("fetch notes Called", "")
        viewModelScope.launch {
            _notesList.value = repository.dao.getNotes()
        }
    }

    fun onNoteClicked(note: Note) {
        _navigateToNoteDetails.value = Unit
        _noteDetails.value = note
    }

}