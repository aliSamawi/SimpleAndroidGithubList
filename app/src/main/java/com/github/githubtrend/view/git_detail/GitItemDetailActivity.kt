package com.github.githubtrend.view.git_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.githubtrend.R
import com.github.githubtrend.data.response.GitItem
import com.github.githubtrend.util.StaticFields
import com.github.githubtrend.util.getAppComponent
import kotlinx.android.synthetic.main.activity_git_item_detail.*
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */
class GitItemDetailActivity : AppCompatActivity(),GitItemDetailView {
    @Inject lateinit var presenter : GitItemDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_item_detail)
        getAppComponent().inject(this)

        presenter.bind(this)

        setupViews()
    }

    fun setupViews(){
        title = "Git Repo's Detail"
        intent.getSerializableExtra(StaticFields.GIT_SELECTED_ITEM)?.let{
            val item = it as GitItem
            Glide.with(this)
                    .load(item.owner.avatar_url)
                    .apply(RequestOptions().placeholder(R.drawable.github_mark).error(R.drawable.github_mark))
                    .into(ivIcon)
            tvStarN.text         = item.stars_count.toString()
            tvName.text          = item.fullName
            tvDescription.text   = item.description
            tvWatcher.text       = "Watcher's Count : " + item.watchers_count.toString()
            tvForks.text         = "Fork's Numbers  : " + item.forks_count.toString()
            tvIssues.text        = "Open Issues' Count : " + item.open_issues_count.toString()
            tvUrl.text           = item.htmlUrl
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destory()
    }
}