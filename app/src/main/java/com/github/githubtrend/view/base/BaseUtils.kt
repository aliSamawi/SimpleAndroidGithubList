package com.github.githubtrend.view.base

import android.content.Context
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.github.githubtrend.R
import com.github.githubtrend.data.model.ErrorModel
import com.github.githubtrend.util.DialogHelper


class BaseUtils {

    private var pageMode: Int = 0
    private var activity: AppCompatActivity? = null
    private var fragment: Fragment? = null
    var loadingDialog: MaterialDialog? = null

    internal constructor(activity: AppCompatActivity) {
        this.activity = activity
        pageMode = ACTIVITY
    }

    internal constructor(fragment: Fragment) {
        this.fragment = fragment
        pageMode = FRAGMENT
    }

    private fun getActivity(): AppCompatActivity? {
        return if (pageMode == ACTIVITY)
            activity
        else
            fragment?.activity as AppCompatActivity
    }

    companion object {
        private val ACTIVITY = 1
        private val FRAGMENT = 2

    }


    fun showError(error: ErrorModel, supportFragmentManager: FragmentManager, showDialog: Boolean = true) {
        if (error.errorCode == 401) {

        }
//        if(showDialog)
//        AlertDialog.getInstance(null,error.errorMessage,getUtilContext().getString(R.string.ok)).show(supportFragmentManager,"DIALOG")
    }


    private fun isContextLive(): Boolean {
        var result = true

        if (pageMode == FRAGMENT && !fragment!!.isAdded)
            result = false
        else if (pageMode == ACTIVITY && activity!!.isFinishing)
            result = false

        return result
    }

    private fun getUtilContext(): Context {
        return if (pageMode == ACTIVITY)
            activity as Context
        else
            fragment!!.context!!
    }

    fun showDialogWithRetry(title: String?, content: String?, retryTitle: String = getUtilContext().getString(R.string.retry), okTitle: String = getUtilContext().getString(R.string.OK), onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        DialogHelper.showDialogWithRetry(getUtilContext(), title ?: "", content ?: "", retryTitle, okTitle, onRetryClick, onOkClick)
    }

    fun showDialogWithRetryByIds(title: Int, content: String?, retryTitle: Int = R.string.retry, okTitle: Int = R.string.OK, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        DialogHelper.showDialogWithRetry(getUtilContext(),
                getUtilContext().getString(title) ?: "",
                content ?: "",
                getUtilContext().getString(retryTitle),
                getUtilContext().getString(okTitle),
                onRetryClick, onOkClick)
    }
//    private val dialogWithRetryClickListeners = DialogInterface.OnClickListener { dialogInterface, i ->
//        run {
//            dialogInterface.dismiss()
//            if (i == DialogInterface.BUTTON_POSITIVE && errorListeners != null) {
//                errorListeners?.onRetryListener()
//            } else {
//                if (i == DialogInterface.BUTTON_NEGATIVE && errorListeners != null) {
//                    errorListeners?.onCancelListener()
//                }
//                else{
//
//                }
//            }
//        }
//    }
//
//    private val dialogSingleButtonClickListener = DialogInterface.OnClickListener { dialogInterface, i ->
//        run{
//            dialogInterface.dismiss()
//            if(i == DialogInterface.BUTTON_POSITIVE && errorOkListener != null)
//                errorOkListener?.onOkListener()
//
//        }
//    }

    fun showLoadingProgressDialog(@Nullable content: String?, @Nullable title: String?) {
        var myTitle = title
        var myContent = content
        if (myTitle == null)
            myTitle = getUtilContext().getString(R.string.connecting)
        if (myContent == null)
            myContent = getUtilContext().getString(R.string.please_wait)

        loadingDialog = DialogHelper.showIndeterminateDialog(getUtilContext(), myTitle!!, myContent!!)
    }

    fun dismissLoadingProgressDialog() {
        loadingDialog?.dismiss()
    }

    fun onTokenExpired() {
        if (!isContextLive())
            return


        dismissLoadingProgressDialog()

//        Toast.makeText(getUtilContext(),"TODO : REDIRECT TO LOGIN",Toast.LENGTH_LONG).show()
//        val intent = Intent(getActivity(), SigninActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        getActivity().startActivity(intent)
//        finishActivity()
    }
}