package com.github.githubtrend.view.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import com.afollestad.materialdialogs.MaterialDialog
import com.github.githubtrend.MyApplication
import com.github.githubtrend.R
import com.github.githubtrend.data.model.ErrorModel
import com.github.githubtrend.injection.component.ActivityComponent
import com.github.githubtrend.injection.component.AppComponent
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity : AppCompatActivity(), Transition.TransitionListener, MvpView
{
    protected var activityComponent: ActivityComponent? = null
    protected val disposables = CompositeDisposable()

    private var baseUtils: BaseUtils? = null
    private var mEndTransitionOp: (() -> Unit)? = null
    private var mAlsoEndTransition = true

    protected abstract fun getLayoutResId(): Int
    protected abstract fun getPresenter(): BasePresenter<*>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(getLayoutResId())
        this.baseUtils = BaseUtils(this)
        activityComponent = getAppComponent().activityComponent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.sharedElementEnterTransition.addListener(this)
        }

    }

    override fun onStop() {
        baseUtils?.dismissLoadingProgressDialog()
        super.onStop()
    }

    override fun onDestroy() {
        activityComponent = null
        getPresenter()?.destroy()
        disposables.dispose()
        super.onDestroy()
    }

    override fun showProgressDialog() {
        baseUtils?.showLoadingProgressDialog(getString(R.string.please_wait), getString(R.string.connecting))
    }

    override fun dismissProgressDialog() {
        baseUtils?.dismissLoadingProgressDialog()
    }

    override fun onTokenExpired() {
        baseUtils?.onTokenExpired()
    }

    override fun showDialogWithRetry(title: String?, content: String?, retryTitle: String, okTitle: String, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        baseUtils?.showDialogWithRetry(title ?: "", content ?: "", retryTitle, okTitle, onOkClick, onRetryClick)
    }

    override fun showDialogWithRetryStringsID(title: Int, content: String?, retryTitle: Int, okTitle: Int, onOkClick: (MaterialDialog) -> Unit, onRetryClick: (MaterialDialog) -> Unit) {
        baseUtils?.showDialogWithRetryByIds(title, content ?: "", retryTitle, okTitle, onOkClick, onRetryClick)
    }
    //transitions
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onTransitionEnd(transition: Transition?) {
        mEndTransitionOp?.invoke()
        if (!mAlsoEndTransition) {
            window.sharedElementEnterTransition.removeListener(this)
        }
    }

    override fun onTransitionResume(transition: Transition?) {
    }

    override fun onTransitionPause(transition: Transition?) {
    }

    override fun onTransitionCancel(transition: Transition?) {
    }

    override fun onTransitionStart(transition: Transition?) {

    }

    override fun showError(error: ErrorModel, showDialog: Boolean) {
        baseUtils?.showError(error,supportFragmentManager,showDialog)
    }

    final fun onActivityTransitionEnd(alsoEndTransition: Boolean = true, op: () -> Unit) {
        mEndTransitionOp = op
        mAlsoEndTransition = alsoEndTransition
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun Context.getAppComponent(): AppComponent = (applicationContext as MyApplication).appComponent

}