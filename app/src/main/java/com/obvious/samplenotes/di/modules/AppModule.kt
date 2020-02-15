package com.obvious.samplenotes.di.modules

import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.obvious.samplenotes.NotesApplication
import com.obvious.samplenotes.db.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Created by Prasoon on 2019-12-11.
 */

@Module
class AppModule {

    @Provides
    @Reusable
    fun provideContext(application: NotesApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun providesWifiManager(context: Context): WifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @Provides
    fun providesConnectivityManager(context: Context): ConnectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

    @Provides
    fun providesClipboardManager(context: Context): ClipboardManager =
        context.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager


}