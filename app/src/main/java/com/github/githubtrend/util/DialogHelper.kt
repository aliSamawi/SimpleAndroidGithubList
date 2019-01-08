package com.github.githubtrend.util

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

/**
 * Created by ali on 1/8/19.
 */
/**
 * Created by ali on 12/13/18.
 */

class DialogHelper{

    companion object
    {
        fun showIndeterminateDialog(context: Context, title: String, message: String) : MaterialDialog
        {
            return MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .show()
        }

        fun showDialogWithRetry(context: Context, title: String, content: String, retryTitle: String, okTitle: String, onRetryClick: (MaterialDialog) -> Unit, onOkCliclk:(MaterialDialog) -> Unit)
        {
            MaterialDialog.Builder(context)
                    .title(title)
                    .content(content)
                    .positiveText(retryTitle)
                    .negativeText(okTitle)
                    .onPositive { dialog, _ ->
                        onRetryClick(dialog)
                    }
                    .onNegative { dialog, _ ->
                        onOkCliclk(dialog)
                    }
                    .show()
        }
    }
}