package com.interview.onchain.repo

import com.google.gson.Gson
import com.interview.onchain.bean.RateInfo
import com.interview.onchain.bean.RateResponse
import com.interview.onchain.bean.WalletInfo
import com.interview.onchain.bean.WalletResponse
import kotlinx.coroutines.delay

class WalletRepo {

    suspend fun getWallet(): List<WalletInfo> {
        //eg:network call
        delay(1000)
        val walletResponse = Gson().fromJson(JsonConfig.WALLET_JSON, WalletResponse::class.java)
        return walletResponse.wallet
    }

    suspend fun getLiveRates(): List<RateInfo> {
        //eg:network call
        delay(1000)
        val rateResponse = Gson().fromJson(JsonConfig.RATE_JSON, RateResponse::class.java)
        return rateResponse.tiers
    }
}