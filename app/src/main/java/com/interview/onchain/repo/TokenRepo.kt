package com.interview.onchain.repo

import com.google.gson.Gson
import com.interview.onchain.bean.TokenBean
import com.interview.onchain.bean.TokenResponse
import kotlinx.coroutines.delay

class TokenRepo {

    suspend fun getToken(): List<TokenBean> {
        //eg:network call
        delay(1000)
        val walletResponse = Gson().fromJson(JsonConfig.TOKEN_JSON, TokenResponse::class.java)
        return walletResponse.currencies
    }
}