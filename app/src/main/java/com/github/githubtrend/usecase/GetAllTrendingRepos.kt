package com.github.githubtrend.usecase

import com.github.githubtrend.data.repository.CloudRepository
import com.github.githubtrend.data.response.GitResponse
import com.github.githubtrend.usecase.base.BaseUsecase
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ali on 1/8/19.
 */
class GetAllTrendingRepos @Inject constructor( private var cloudRepository: CloudRepository)
    : BaseUsecase<GitResponse>() {

    var date : String = "2018-07-17" //default time

    override fun createObservable(): Single<GitResponse> {
        return cloudRepository.getTrendingRepo(date)
    }

}