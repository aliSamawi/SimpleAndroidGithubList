package com.github.githubtrend.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by ali on 8/17/2018 AD.
 */
data class GitResponse( @SerializedName("total_count") var total_count : Int,
                        @SerializedName("items") var items : ArrayList<GitItem>
)