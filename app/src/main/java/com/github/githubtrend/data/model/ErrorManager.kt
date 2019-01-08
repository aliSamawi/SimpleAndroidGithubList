package com.github.githubtrend.data.model

import android.accounts.NetworkErrorException
import com.github.githubtrend.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by ali on 1/8/19.
 */
class ErrorManager {

    object ErrorCodes {
        val ERROR_GENERAL = 400
        val ERROR_AUTH_ERROR = 401
        val ERROR_NETWORK = 404
        val ERROR_INTERNAL_SERVER = 500
    }

    companion object {

        fun handleError(e: Throwable): ErrorModel {
            val errorModel: ErrorModel
            if (e is HttpException) {
                val response = e.response()
                val errorCode = response.code()
                val errorMessage = StringBuilder()

                try {
                    val errorJsonObject = JSONObject(response.errorBody()?.string())
//                    errorMessage.append(errorJsonObject.toString())
                    val errorsArray: JSONArray = errorJsonObject.getJSONArray("errors")
                    if(errorsArray.length()==1)
                        errorMessage.append(errorsArray.getString(0))
                    else
                        for (i in 0 until errorsArray.length()) {
                            errorMessage.append("${errorsArray.getString(i)}\n")
                        }
                } catch (e: Exception) {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace()
                    }
                }
                var result = errorMessage.toString()
                if (result.isBlank()) {
                    result = "Error [$errorCode]"
                }
                errorModel = ErrorModel(NetworkErrorException(), errorCode, result, false)

            } else {
                errorModel = ErrorModel(Exception(), ErrorCodes.ERROR_GENERAL, "Connection error!", true)
            }
            return errorModel
        }

    }

}