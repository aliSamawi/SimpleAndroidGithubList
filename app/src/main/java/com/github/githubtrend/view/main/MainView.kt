package com.github.githubtrend.view.main

import com.github.githubtrend.data.response.GitResponse

/**
 * Created by ali on 8/17/2018 AD.
 */
interface MainView {
    fun onGetGitsItems(gitResponse: GitResponse)
    fun onGetError(e:Throwable)
    fun showLoading()
    fun hideLoading()
}