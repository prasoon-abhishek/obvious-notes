package com.obvious.samplenotes.di

import com.khoslalabs.veri5id.shared.data.di.ViewModelModule
import com.obvious.samplenotes.NotesApplication
import com.obvious.samplenotes.di.modules.ActivityBindingModule
import com.obvious.samplenotes.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Prasoon on 2019-12-11.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<NotesApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NotesApplication>()
}