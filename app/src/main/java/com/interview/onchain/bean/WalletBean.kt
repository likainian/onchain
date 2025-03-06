package com.interview.onchain.bean

import com.interview.onchain.base.BaseResponse

data class WalletResponse(
    val wallet: List<WalletInfo>
): BaseResponse()

data class WalletInfo(
    val currency: String,
    val amount: String,
)

data class RateResponse(
    val tiers: List<RateBean>
): BaseResponse()

data class RateBean(
    val from_currency:String,
    val to_currency:String,
    val rates:List<RateInfo>,
)

data class RateInfo(
    val amount: String,
    val rate: String

)
