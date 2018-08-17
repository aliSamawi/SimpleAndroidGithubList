package com.github.githubtrend.data.repository

import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.data.restful.APIs
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ali on 8/17/2018 AD.
 */

class CloudRepository @Inject constructor(
        private val api: APIs
) {

    fun getTrendingRepo(date : String): Single<GitResponse>
            = Single.defer { api.getTrending("stars" ,
            "desc" ,
            "q=language:kotlin+created:>$date") }
}