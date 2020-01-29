package ru.suleymanovtat.dressingbabyweather.presentation.base

import android.app.ActionBar
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.suleymanovtat.dressingbabyweather.App
import ru.suleymanovtat.dressingbabyweather.BuildConfig
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.utils.hideKeyboardEx
import ru.suleymanovtat.dressingbabyweather.utils.showSnack
import ru.suleymanovtat.dressingbabyweather.utils.showToast

abstract class BaseFragment(layout: Int) : Fragment(layout) {

    private val appBar: ActionBar? = activity?.actionBar
    protected fun disableHomeAsUp() = appBar?.setDisplayHomeAsUpEnabled(false)
    protected fun showToast(text: String) = activity?.showToast(text)
    protected fun showSnack(text: String) = activity?.showSnack(text)
    protected fun hideKeyboard() = activity?.hideKeyboardEx()

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((requireActivity().application as App).getViewModelComponent())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    public fun sendMessage() {
        val uri = Uri.fromParts("mailto", BuildConfig.EMAIL, null)
        val emailIntent = Intent(Intent.ACTION_SENDTO, uri).apply {
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.no_my_city))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.my_locality))
        }
        startActivity(emailIntent)
    }
}