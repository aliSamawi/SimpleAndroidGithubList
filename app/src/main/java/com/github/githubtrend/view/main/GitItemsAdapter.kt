package com.github.githubtrend.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.githubtrend.R
import com.github.githubtrend.data.response.GitItem

/**
 * Created by ali on 8/17/2018 AD.
 */
class GitItemsAdapter ( var mContext : Context , var items : List<GitItem>) :
        RecyclerView.Adapter<GitItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context).inflate( R.layout.item_git_layout
                , parent , false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(mContext , item)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var tvName : TextView
        var tvStar : TextView
        var imgIcon: ImageView

        init {
            tvName = view.findViewById(R.id.tvName)
            tvStar = view.findViewById(R.id.tvStarN)
            imgIcon = view.findViewById(R.id.ivIcon)
        }

        fun bind( mContext: Context , item : GitItem){
            tvName.text = item.name
            tvStar.text = item.stars_count.toString()

            item.owner.avatar_url?.let {
                Glide.with(mContext)
                        .load(it)
                        .into(imgIcon)
            }

        }
    }
}