package com.github.githubtrend.view.git_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.githubtrend.R
import com.github.githubtrend.util.getAppComponent
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */
class GitItemDetailActivity : AppCompatActivity(),GitItemDetailView {
    @Inject lateinit var presenter : GitItemDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppComponent().inject(this)

        presenter.bind(this)

        setupViews()
    }

    fun setupViews(){

    }
}