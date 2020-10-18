package com.dikiy.workshop.code.classes

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.BackgroundColorSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dikiy.workshop.R
import java.util.regex.Matcher

class ActivitySpannable : AppCompatActivity() {

    private var end = 0
    private var start = 0
    private val text = "Follow https://backit.me/cashback/orders-search by clicking on link"
    private val TAG = "Logs"

    private lateinit var url: String
    private lateinit var matcher: Matcher
    private lateinit var spannable: SpannableString

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable)
        matcher = Patterns.WEB_URL.matcher(text)
        spannable = SpannableString(text)
        while (matcher.find()) {
            url = matcher.group()
            start = matcher.start()
            end = matcher.end()
            Log.d(TAG, "Url extracted: $url")
            Log.d(TAG, "Starting index: $start")
            Log.d(TAG, "Ending index: $end")
            spannable.setSpan(URLSpan(url),
                    start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(object : UnderlineSpan() {
                override fun updateDrawState(tp: TextPaint) {
                    tp.isUnderlineText = false
                }
            }, start, end, 0)
            spannable.setSpan(BackgroundColorSpan(Color.rgb(207, 252, 0)),
                    start, end, 0)
        }
        findViewById<TextView>(R.id.tv_spannable).text = spannable
    }
}
