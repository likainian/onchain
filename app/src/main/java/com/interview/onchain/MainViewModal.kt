package com.interview.onchain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.interview.onchain.entry.TokenEntry
import com.interview.onchain.repo.TokenRepo
import com.interview.onchain.repo.WalletRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModal(application: Application) : AndroidViewModel(application) {

    val totalLiveData = MutableLiveData<String>()
    val tokenLiveData = MutableLiveData<ArrayList<TokenEntry>>()

    val walletRepo = WalletRepo()
    val tokenRepo = TokenRepo()

    fun getWalletInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            val walletList = async { walletRepo.getWallet() }.await()
            val rateList = async { walletRepo.getLiveRates() }.await()
            val tokenList = async { tokenRepo.getToken() }.await()
            totalLiveData.postValue("36.68")
            tokenLiveData.postValue(
                arrayListOf(
                    TokenEntry(
                        "https://s3-ap-southeast-1.amazonaws.com/monaco-cointrack-production/uploads/coin/colorful_logo/5c1246f55568a400e48ac233/bitcoin.png",
                        "BTC",
                        "1000",
                        "btc",
                        "1.2",
                    ),
                    TokenEntry(
                        "https://s3-ap-southeast-1.amazonaws.com/monaco-cointrack-production/uploads/coin/colorful_logo/5c1246f55568a400e48ac233/bitcoin.png",
                        "BTC",
                        "1000",
                        "btc",
                        "1.2",
                    ),
                    TokenEntry(
                        "https://s3-ap-southeast-1.amazonaws.com/monaco-cointrack-production/uploads/coin/colorful_logo/5c1246f55568a400e48ac233/bitcoin.png",
                        "BTC",
                        "1000",
                        "btc",
                        "1.2",
                    ),
                )
            )
        }

    }
}