package com.github.githubtrend.view.main

import android.util.Log
import com.github.githubtrend.data.repository.CloudRepository
import com.github.githubtrend.data.response.GitResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */

class MainPresenter @Inject constructor( private var cloudRepository: CloudRepository ) {

    var view : MainView? = null
    private var disposable : Disposable? = null

    fun bind(view : MainView){
        this.view = view
    }

    fun getAllTrendingRepos(){
        cloudRepository.getTrendingRepo("2018-07-17") //todo set time
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GitResponse> {

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onSuccess(t: GitResponse) {
                        Log.d("response","")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }
}