package com.github.githubtrend.view.main

import com.github.githubtrend.data.restful.APIs
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */

class MainPresenter @Inject constructor( var apIs: APIs ) {

    var view : MainView? = null

    fun bind(view : MainView){
        this.view = view
    }
}