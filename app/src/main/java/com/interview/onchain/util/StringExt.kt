package com.interview.onchain.util

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt


fun String.setForeTextColor(word: String?, @ColorInt color: Int): SpannableStringBuilder {
    val span = SpannableStringBuilder(this)
    if (word.isNullOrEmpty()) return span
    var startIndex = 0
    while (startIndex != -1) {
        val index = span.indexOf(word, startIndex)
        startIndex = index + word.length
        if (index != -1) {
            span.setSpan(
                ForegroundColorSpan(color),
                index, index + word.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        } else {
            startIndex = -1
        }
    }

    return span
}

