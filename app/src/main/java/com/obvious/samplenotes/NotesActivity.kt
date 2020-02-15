package com.obvious.samplenotes

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.obvious.samplenotes.utils.viewModelProvider
import com.obvious.samplenotes.viewmodels.NotesViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NotesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = viewModelProvider(viewModelFactory)

    }

    override fun onBackPressed() {
        when (findNavController(R.id.navHostFragment).currentDestination?.id) {
            R.id.addNoteFragment -> viewModel.navigateToNotes()
            R.id.noteDetailsFragment -> viewModel.navigateToNotes()
//            else -> super.onBackPressed()

            R.id.notesFragment -> finish()
        }
    }
}
