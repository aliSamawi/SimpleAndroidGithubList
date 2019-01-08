package com.github.githubtrend.view.main

import com.github.githubtrend.data.repository.CloudRepository
import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.view.base.BasePresenter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */

class MainPresenter @Inject constructor( private var cloudRepository: CloudRepository )
    : BasePresenter<MainView>(){

    private var disposable : Disposable? = null

    fun getAllTrendingRepos(){
        view?.showLoading()
        cloudRepository.getTrendingRepo("2018-07-17") //todo set time
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GitResponse> {

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onSuccess(t: GitResponse) {
                        view?.onGetGitsItems(t)
                        view?.hideLoading()
                    }

                    override fun onError(e: Throwable) {
                        view?.onGetError(e)
                        view?.hideLoading()
                    }
                })
    }

}