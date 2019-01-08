package com.github.githubtrend.injection.component

import com.github.githubtrend.injection.scope.PerActivity
import com.github.githubtrend.view.git_detail.GitItemDetailActivity
import com.github.githubtrend.view.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: GitItemDetailActivity)

}