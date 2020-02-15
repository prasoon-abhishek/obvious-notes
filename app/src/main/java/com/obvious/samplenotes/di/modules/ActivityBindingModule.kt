package com.obvious.samplenotes.di.modules

import com.obvious.samplenotes.NotesActivity
import com.obvious.samplenotes.di.scopes.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Prasoon on 2019-12-12.
 */

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [@ActivityScoped].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): NotesActivity
}