package com.github.githubtrend.data.restful

import com.github.githubtrend.data.response.GitResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ali on 8/17/2018 AD.
 */
interface APIs {

    @GET("search/repositories")
    fun getTrending(
        @Query("sort") sort : String ,
        @Query("order") order : String ,
        @Query("q") query : String
    ) : Single<GitResponse>
}