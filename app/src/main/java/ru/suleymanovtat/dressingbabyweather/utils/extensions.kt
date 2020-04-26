package ru.suleymanovtat.dressingbabyweather.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}

fun Activity.hideKeyboardEx() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.apply { imm.hideSoftInputFromWindow(windowToken, 0) }
}

fun Activity.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
fun Activity.showSnack(text: String) =
    Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(this, message, length).show()

inline fun View.snack(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    f: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}

fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
    try {
        collect { value ->
            Log.e("my", "collect " + value)
            emit(value)
        }
    } catch (e: Throwable) {
        Log.e("my", "handleErrors " + e.message)
    }
}