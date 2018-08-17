package com.github.githubtrend.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.githubtrend.R
import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.util.getAppComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),MainView {

    @Inject lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppComponent().inject(this)

        presenter.bind(this)
        presenter.getAllTrendingRepos()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun onGetGitsItems(gitResponse: GitResponse) {
        //todo
    }

    override fun onGetError(e: Throwable) {
        //todo
    }

    override fun showLoading() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingBar.visibility = View.GONE
    }
}
