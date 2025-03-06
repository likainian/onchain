package com.interview.onchain.base

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.delay

open class BaseRepo {
    suspend inline fun <reified T : BaseResponse> parseJson(json: String, clazz: Class<T>): T? {
        //eg:network call
        delay(100)
        val response = kotlin.runCatching {
            Gson().fromJson(json, clazz)
        }.getOrNull()
        return if (response?.ok == true) {
            response
        } else {
            Log.i("error", response?.warning ?: "")
            null
        }
    }
}