package com.github.githubtrend.util

import android.content.Context
import com.github.githubtrend.MyApplication
import com.github.githubtrend.injection.component.AppComponent

/**
 * Created by ali on 8/17/2018 AD.
 */

fun Context.getAppComponent(): AppComponent = (applicationContext as MyApplication).appComponent