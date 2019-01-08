package com.github.githubtrend.view.base

import com.afollestad.materialdialogs.MaterialDialog
import com.github.githubtrend.data.model.ErrorModel

interface MvpView {
    fun showProgressDialog() {}
    fun dismissProgressDialog() {}
    fun showError(error: ErrorModel, showDialog: Boolean = true)
    fun showDialogWithRetry(title: String?, content: String?, retryTitle: String, okTitle: String, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {}
    fun showDialogWithRetryStringsID(title: Int, content: String?, retryTitle: Int, okTitle: Int, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {}
    fun onTokenExpired() {}

}