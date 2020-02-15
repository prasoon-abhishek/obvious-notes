package com.khoslalabs.veri5id.shared.data.di

import androidx.lifecycle.ViewModelProvider
import com.obvious.samplenotes.NotesViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [MarathonViewModelFactory].
 */
@Module
@Suppress("UNUSED")
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: NotesViewModelFactory):
        ViewModelProvider.Factory
}
