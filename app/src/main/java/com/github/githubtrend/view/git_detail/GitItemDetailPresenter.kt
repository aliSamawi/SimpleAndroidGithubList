package com.github.githubtrend.view.git_detail

import io.reactivex.disposables.Disposable

/**
 * Created by ali on 8/17/2018 AD.
 */
class GitItemDetailPresenter {
    var view : GitItemDetailView? = null
    private var disposable : Disposable? = null

    fun bind(view : GitItemDetailView){
        this.view = view
    }

    fun destory(){
        disposable?.dispose()
    }
}