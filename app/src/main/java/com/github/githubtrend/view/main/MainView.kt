package com.github.githubtrend.view.main

import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.view.base.MvpView

/**
 * Created by ali on 8/17/2018 AD.
 */
interface MainView : MvpView {
    fun onGetGitsItems(gitResponse: GitResponse)
    fun onGetError(errorMessage : String)
    fun showLoading()
    fun hideLoading()
}