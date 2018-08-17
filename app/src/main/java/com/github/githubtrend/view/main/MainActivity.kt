package com.github.githubtrend.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.githubtrend.R
import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.util.getAppComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),MainView {

    @Inject lateinit var presenter : MainPresenter

    var adapter : GitItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getAppComponent().inject(this)

        presenter.bind(this)

        setupViews()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    fun setupViews(){
        adapter = GitItemsAdapter(this@MainActivity, arrayListOf()
                    , {
                        it?.let {
                            //todo
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

    override fun onGetError(e: Throwable) {
        Snackbar.make( holder ,"there is something wrong on getting data" , Snackbar.LENGTH_SHORT)
                .show()
    }

    override fun showLoading() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingBar.visibility = View.GONE
    }
}
