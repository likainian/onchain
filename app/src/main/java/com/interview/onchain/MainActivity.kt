package com.interview.onchain

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.onchain.adapter.TokenAdapter
import com.interview.onchain.databinding.ActivityMainBinding
import com.interview.onchain.util.setForeTextColor

class MainActivity : AppCompatActivity() {

    private val mBinding by lazy {
        DataBindingUtil.inflate<ActivityMainBinding>(
            layoutInflater,
            R.layout.activity_main,
            null,
            false
        )
            .apply {
                lifecycleOwner = this@MainActivity
            }
    }

    private val mViewModel by lazy {
        ViewModelProvider(this)[MainViewModal::class.java]
    }

    private val adapter by lazy {
        TokenAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = adapter

        mViewModel.getWalletInfo()
        mViewModel.totalLiveData.observe(this) {
            mBinding.tvTotal.text = "\$ $it USD".setForeTextColor(it, Color.WHITE)
        }
        mViewModel.tokenLiveData.observe(this) {
            adapter.data = it
            adapter.notifyDataSetChanged()
        }
    }
}