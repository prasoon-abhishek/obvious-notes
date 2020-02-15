package com.obvious.samplenotes.di.modules

import androidx.lifecycle.ViewModel
import com.khoslalabs.veri5id.shared.data.di.FragmentScoped
import com.khoslalabs.veri5id.shared.data.di.ViewModelKey
import com.obvious.samplenotes.ui.AddNoteFragment
import com.obvious.samplenotes.ui.NoteDetailsFragment
import com.obvious.samplenotes.ui.NotesFragment
import com.obvious.samplenotes.viewmodels.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): NotesFragment

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    internal abstract fun contributeNotesViewModel(viewModel: NotesViewModel): ViewModel

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAddNoteFragment(): AddNoteFragment

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeNoteDetailsFragment(): NoteDetailsFragment
}
