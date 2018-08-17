package com.github.githubtrend.view.git_detail

import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */
class GitItemDetailPresenter @Inject constructor() {
    var view : GitItemDetailView? = null
    private var disposable : Disposable? = null

    fun bind(view : GitItemDetailView){
        this.view = view
    }

    fun destory(){
        disposable?.dispose()
    }
}