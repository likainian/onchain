package com.interview.onchain.bean

data class WalletResponse(
    val ok: Boolean,
    val warning: String,
    val wallet: List<WalletInfo>
)

data class WalletInfo(
    val currency: String,
    val amount: String,
)

data class RateResponse(
    val ok: Boolean,
    val warning: String,
    val tiers: List<RateInfo>
)

data class RateInfo(
    val amount: String,
    val rate: String

)
