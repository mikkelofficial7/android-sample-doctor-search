package com.android.mobile.alteacaretest.extension

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned

fun String?.toStringHtml() : Spanned {
    return when {
        this.isNullOrEmpty() -> SpannableString("")
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        else -> Html.fromHtml(this)
    }
}