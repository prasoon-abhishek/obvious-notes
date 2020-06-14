package com.obvious.samplenotes.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.obvious.samplenotes.R
import com.obvious.samplenotes.utils.hideKeyboard
import com.obvious.samplenotes.utils.showToast
import com.obvious.samplenotes.utils.viewModelProvider
import com.obvious.samplenotes.viewmodels.EventObserver
import com.obvious.samplenotes.viewmodels.NotesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_note_details.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NotesViewModel

    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModelProvider(viewModelFactory)

        viewModel.noteDetails.observe(
            this,
            EventObserver {
                etTitle.setText(it.title)
                etContent.setText(it.content)
                isUpdate = true
                Log.d("isUpdate", "isUpdate")
            }
        )

        fabAddNote.setOnClickListener {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            if (isUpdate) {
                viewModel.updateNote(title, content)
            } else {
                viewModel.saveNote(title, content)
            }
            hideKeyboard()
        }

        viewModel.showError.observe(
            this,
            EventObserver {
                showToast(it)
            }
        )

        viewModel.showToast.observe(
            this,
            EventObserver {
                showToast(it)
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
