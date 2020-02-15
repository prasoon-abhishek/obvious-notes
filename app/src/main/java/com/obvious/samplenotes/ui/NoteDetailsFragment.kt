package com.obvious.samplenotes.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.obvious.samplenotes.R
import com.obvious.samplenotes.utils.viewModelProvider
import com.obvious.samplenotes.viewmodels.EventObserver
import com.obvious.samplenotes.viewmodels.NotesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_note_details.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class NoteDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        viewModel.noteDetails.observe(
            this,
            EventObserver {
                tvTitle.text = it.title
                tvContent.text = it.content
            }
        )

        viewModel.navigateToNotes.observe(
            this,
            EventObserver {
                findNavController().navigate(R.id.notesFragment)
            }
        )
    }

}
