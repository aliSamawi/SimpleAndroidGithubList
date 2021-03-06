package com.github.githubtrend.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by ali on 8/17/2018 AD.
 */
data class GitItem( @SerializedName("id") var id : Int ,
                    @SerializedName("name") var name : String ,
                    @SerializedName("full_name") var fullName : String ,
                    @SerializedName("description") var description : String ,
                    @SerializedName("html_url") var htmlUrl : String ,
                    @SerializedName("created_at") var created_at : String ,
                    @SerializedName("watchers_count") var watchers_count : Int ,
                    @SerializedName("open_issues_count") var open_issues_count : Int ,
                    @SerializedName("forks_count") var forks_count : Int ,
                    @SerializedName("stargazers_count") var stars_count : Int ,
                    @SerializedName("owner") var owner : OwnerGitItem
) : Serializable