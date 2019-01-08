package com.github.githubtrend.data.model

/**
 * Created by ali on 1/8/19.
 */
data class ErrorModel(
        val e: Exception?,
        val errorCode: Int,
        val errorMessage: String,
        val isConnectionError: Boolean
)