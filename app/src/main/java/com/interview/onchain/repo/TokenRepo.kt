package com.interview.onchain.repo

import com.interview.onchain.base.BaseRepo
import com.interview.onchain.bean.TokenBean
import com.interview.onchain.bean.TokenResponse

class TokenRepo : BaseRepo() {

    suspend fun getToken(): List<TokenBean>? {
        val walletResponse = parseJson(JsonConfig.TOKEN_JSON, TokenResponse::class.java)
        return walletResponse?.currencies
    }
}