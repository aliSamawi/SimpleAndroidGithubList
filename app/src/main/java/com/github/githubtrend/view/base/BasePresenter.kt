package com.github.githubtrend.view.base

import com.github.githubtrend.injection.component.AppComponent
import com.github.githubtrend.injection.component.PresenterComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> {
    protected val disposables = CompositeDisposable()
    protected var view: T? = null
    protected var presenterComponent: PresenterComponent? = null

    fun addToDisposables(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun bind(view: T, appComponent: AppComponent) {
        presenterComponent = appComponent.presenterComponent()
        injectComponents()
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    open fun destroy() {
        presenterComponent = null
        disposables.clear()
        unbind()
    }

    protected open fun injectComponents() {}
}