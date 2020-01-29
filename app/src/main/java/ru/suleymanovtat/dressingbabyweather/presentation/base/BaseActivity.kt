package ru.suleymanovtat.dressingbabyweather.presentation.base

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.suleymanovtat.dressingbabyweather.App
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.utils.hideKeyboardEx
import java.util.*

abstract class BaseActivity(layout: Int) : AppCompatActivity(layout) {
    protected open val PERMISSION_REQUEST = 5

    open var arrayPermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    open lateinit var messageRationalePermission: String
    open lateinit var messageNecessaryPermissions: String
    protected var requestCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDaggerDependencies()
    }

    private fun hideKeyboard() = this.hideKeyboardEx()

    @TargetApi(Build.VERSION_CODES.M)
    fun isStoragePermissionGranted(requestCode: Int = 0): Boolean {
        this.requestCode = requestCode
        if (checkPermissionList()) {
            return true
        } else {
            requestPermission()
            return false
        }
    }

    fun checkPermissionList(): Boolean {
        val list = ArrayList<Boolean>()
        arrayPermission.forEach {
            list.add(
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            )
        }
        return list.all { it }
    }

    open fun openNeededАction(requestCodeIntent: Int) {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, requestCodeIntent)
    }

    fun checkPermissionRationaleList(): Boolean {
        val list = ArrayList<Boolean>()
        arrayPermission.forEach {
            list.add(ActivityCompat.shouldShowRequestPermissionRationale(this, it))
        }
        return list.all { it }
    }

    fun showNoGalleryPermission() {
        openApplicationSettings()
        Toast.makeText(this, messageNecessaryPermissions, Toast.LENGTH_LONG).show()
    }

    fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(appSettingsIntent, PERMISSION_REQUEST)
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayPermission,
            PERMISSION_REQUEST
        )
    }

    protected abstract fun injectDependency(component: ViewModelComponent)

    private fun createDaggerDependencies() {
        injectDependency((application as App).getViewModelComponent())
    }
}