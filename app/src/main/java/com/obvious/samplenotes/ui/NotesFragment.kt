package com.obvious.samplenotes.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.obvious.samplenotes.R
import com.obvious.samplenotes.adapters.NotesAdapter
import com.obvious.samplenotes.models.Note
import com.obvious.samplenotes.utils.viewModelProvider
import com.obvious.samplenotes.viewmodels.EventObserver
import com.obvious.samplenotes.viewmodels.NotesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A simple [Fragment] subclass.
 */
@Singleton
class NotesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NotesViewModel

    private val notesList = arrayListOf<Note>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }

        val notesAdapter = NotesAdapter(notesList) { note ->
            viewModel.onNoteClicked(note)
        }
        rvNotes.layoutManager = LinearLayoutManager(activity!!)
        rvNotes.adapter = notesAdapter

        viewModel.fetchNotes()

        viewModel.notesList.observe(
            this,
            Observer {
                notesList.clear()
                notesList.addAll(it)
                notesAdapter.notifyDataSetChanged()
            }
        )

        viewModel.navigateToNoteDetails.observe(
            this,
            EventObserver {
                findNavController().navigate(R.id.noteDetailsFragment)
            }
        )
    }

}
