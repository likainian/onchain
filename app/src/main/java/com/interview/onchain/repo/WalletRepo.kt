package com.interview.onchain.repo

import com.interview.onchain.base.BaseRepo
import com.interview.onchain.bean.RateBean
import com.interview.onchain.bean.RateResponse
import com.interview.onchain.bean.WalletInfo
import com.interview.onchain.bean.WalletResponse

class WalletRepo : BaseRepo() {

    suspend fun getWallet(): List<WalletInfo>? {
        val walletResponse = parseJson(JsonConfig.WALLET_JSON, WalletResponse::class.java)
        return walletResponse?.wallet
    }

    suspend fun getLiveRates(): List<RateBean>? {
        val rateResponse = parseJson(JsonConfig.RATE_JSON, RateResponse::class.java)
        return rateResponse?.tiers
    }
}