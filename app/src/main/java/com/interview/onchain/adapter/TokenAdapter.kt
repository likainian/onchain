package com.interview.onchain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.interview.onchain.R
import com.interview.onchain.databinding.ItemTokenBinding
import com.interview.onchain.entry.TokenEntry

class TokenAdapter : RecyclerView.Adapter<TokenAdapter.TokenViewHolder>() {

    var data = ArrayList<TokenEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokenViewHolder {
        val bind = DataBindingUtil.inflate<ItemTokenBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_token,
            parent,
            false
        )
        return TokenViewHolder(bind)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TokenViewHolder, position: Int) {
        val bind = holder.bind as ItemTokenBinding
        Glide.with(bind.ivLogo).load(data[position].logo).error(R.mipmap.logo).into(bind.ivLogo)
        bind.tvName.text = data[position].name
        bind.tvAmount.text = data[position].amount
        bind.tvSymbol.text = data[position].symbol
        bind.tvUsd.text = "$ " + data[position].usd
    }

    class TokenViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
        val bind = itemView
    }
}