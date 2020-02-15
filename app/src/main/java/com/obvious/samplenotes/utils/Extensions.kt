package com.obvious.samplenotes.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.obvious.samplenotes.viewmodels.Event


/**
 * Created by Prasoon on 2020-02-10.
 */


/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

fun Fragment.showToast(msg: String) {
    Snackbar.make(this.view!!, msg, Snackbar.LENGTH_SHORT).show()
}

fun <T> LiveData<T>.event() = this.map { Event(it) }

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

fun Fragment.hideKeyboard() {
    val imm = this.activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.activity?.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}