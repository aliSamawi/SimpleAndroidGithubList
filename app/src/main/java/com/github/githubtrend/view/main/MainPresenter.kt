package com.github.githubtrend.view.main

import com.github.githubtrend.usecase.GetAllTrendingRepos
import com.github.githubtrend.view.base.BasePresenter
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */

class MainPresenter @Inject constructor( private val getAllTrendingRepos: GetAllTrendingRepos)
    : BasePresenter<MainView>(){

    fun getAllTrendingRepos(){

        view?.showLoading()
        getAllTrendingRepos.apply {
            this.date = "2018-07-17" //todo set time
        }.observe( {
            view?.onGetGitsItems(it)

        } , {
            view?.onGetError(it.errorMessage)
        } , {
            view?.hideLoading()
        }).register(this)

    }

}