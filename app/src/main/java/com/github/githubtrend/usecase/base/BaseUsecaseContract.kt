package com.github.githubtrend.usecase.base

import com.github.githubtrend.data.model.ErrorModel


/**
 * Created by ali_samawi on 3/21/2017.
 */
interface BaseUsecaseContract<T> {
    val isCompleted: Boolean
    val isInProgress: Boolean
    val isFailed: Boolean
    fun cancel()
    fun observe(onSuccess: (T) -> Unit,
                onError: (e: ErrorModel) -> Unit,
                onFinished: () -> Unit): BaseUsecase<T>
}
