package ru.suleymanovtat.dressingbabyweather.presentation.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.suleymanovtat.dressingbabyweather.R

class DialogAppFragment : DialogFragment() {

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_MESSAGE = "message"
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = arguments
        return AlertDialog.Builder(activity!!, R.style.MainAlertDialogTheme)
            .setTitle(args!!.getString(KEY_TITLE))
            .setMessage(args.getString(KEY_MESSAGE))
            .setPositiveButton(R.string.yes, { dialog, which ->
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, null)
            })
            .setNegativeButton(R.string.no, { dialog, which ->
                targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
            })
            .create()
    }
}
