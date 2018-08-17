package com.github.githubtrend.data.response

import com.google.gson.annotations.SerializedName

/**
 * Created by ali on 8/17/2018 AD.
 */
data class OwnerGitItem(@SerializedName("id") var id : Int,
                        @SerializedName("login") var login : String,
                        @SerializedName("avatar_url") var avatar_url : String?,
                        @SerializedName("html_url") var url : String?
                        )