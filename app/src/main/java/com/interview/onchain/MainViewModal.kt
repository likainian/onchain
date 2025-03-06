package com.interview.onchain

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.interview.onchain.base.BaseViewModel
import com.interview.onchain.entry.TokenEntry
import com.interview.onchain.repo.TokenRepo
import com.interview.onchain.repo.WalletRepo
import kotlinx.coroutines.async
import java.math.BigDecimal

class MainViewModal(application: Application) : BaseViewModel(application) {

    val totalLiveData = MutableLiveData<String>()
    val tokenLiveData = MutableLiveData<MutableList<TokenEntry>>()

    private val walletRepo = WalletRepo()
    private val tokenRepo = TokenRepo()

    fun getWalletInfo() {
        addLaunch {
            val walletList = async { walletRepo.getWallet() }.await()
            val rateList = async { walletRepo.getLiveRates() }.await()
            val tokenList = async { tokenRepo.getToken() }.await()

            val rateMap = rateList?.associateBy { it.from_currency }
            val tokenMap = tokenList?.associateBy { it.coin_id }
            var total = BigDecimal.ZERO
            val token = walletList?.map {
                val rate = rateMap?.get(it.currency)?.rates?.firstOrNull()
                val usd = if (rate != null) {
                    val amount = rate.amount.toBigDecimalOrNull()
                    if (amount == null || amount == BigDecimal.ZERO) {
                        BigDecimal.ZERO
                    } else {
                        it.amount.toBigDecimal().multiply(rate.rate.toBigDecimalOrNull())
                    }
                } else {
                    BigDecimal.ZERO
                }
                total += usd
                TokenEntry(
                    tokenMap?.get(it.currency)?.colorful_image_url ?: "",
                    it.currency,
                    it.amount,
                    tokenMap?.get(it.currency)?.symbol ?: "",
                    usd.toString()
                )
            }?.toMutableList()
            totalLiveData.postValue(total.toString())
            token?.let {
                tokenLiveData.postValue(it)
            }
        }

    }
}