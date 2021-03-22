package com.dtac.examination.app.extension

import android.app.Activity
import android.app.ProgressDialog

var progressDialog: ProgressDialog? = null

fun Activity.showProgress() {
    progressDialog = ProgressDialog(this)
    progressDialog?.let {
        it.show()
    }
}

fun Activity.dismissProgress() {
    progressDialog?.let {
        it.dismiss()
    }
}