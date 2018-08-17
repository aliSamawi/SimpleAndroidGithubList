package com.github.githubtrend.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.githubtrend.R
import com.github.githubtrend.util.getAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity(),MainView {

    @Inject lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppComponent().inject(this)

        presenter.bind(this)
    }
}
