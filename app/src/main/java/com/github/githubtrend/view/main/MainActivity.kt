package com.github.githubtrend.view.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.githubtrend.R
import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.util.StaticFields
import com.github.githubtrend.view.base.BaseActivity
import com.github.githubtrend.view.base.BasePresenter
import com.github.githubtrend.view.git_detail.GitItemDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(),MainView {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun getPresenter(): BasePresenter<*>? = presenter

    @Inject lateinit var presenter : MainPresenter

    var adapter : GitItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getAppComponent().activityComponent().inject(this)
        presenter.bind(this , getAppComponent())

        setupViews()
    }

    fun setupViews(){
        title = "Git Repositories"
        adapter = GitItemsAdapter(this@MainActivity, arrayListOf()
                    , {
                        it?.let {
                            val intent = Intent( this@MainActivity , GitItemDetailActivity::class.java )
                            intent.putExtra( StaticFields.GIT_SELECTED_ITEM , it)
                            startActivity(intent)
                        }
                })
        with(recyclerView){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MainActivity.adapter
        }
        presenter.getAllTrendingRepos()
    }

    override fun onGetGitsItems(gitResponse: GitResponse) {
        adapter?.items = gitResponse.items
        adapter?.notifyDataSetChanged()
    }

    override fun onGetError(errorMessage : String) {
        Snackbar.make( holder , errorMessage , Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showLoading() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingBar.visibility = View.GONE
    }
}
