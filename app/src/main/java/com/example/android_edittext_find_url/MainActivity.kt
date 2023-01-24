package com.example.android_edittext_find_url

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    lateinit var et_url: EditText
    lateinit var tv_url: TextView
    lateinit var bn_url: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        et_url = findViewById(R.id.et_url)
        tv_url = findViewById(R.id.tv_url)
        bn_url = findViewById(R.id.bn_url)
        bn_url = findViewById(R.id.bn_url)


        et_url.setText("I would like to do something similar to the https://twitter.com app")

        bn_url.setOnClickListener {
            findUrl()
        }

        et_url.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tv_url.text = p0
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
    private fun findUrl() {
        val pattern: Pattern = Pattern.compile(
            "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                    "(\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                    "|mil|biz|info|mobi|name|aero|jobs|museum" +
                    "|travel|[a-z]{2}))(:[\\d]{1,5})?" +
                    "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                    "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                    "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                    "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                    "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                    "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b"
        )
        val matcher: Matcher = pattern.matcher(et_url.getText())
        while (matcher.find()) {
            val spannableStr = SpannableString(et_url.getText())
            val foregroundColorSpan = ForegroundColorSpan(Color.GREEN)
            spannableStr.setSpan(
                foregroundColorSpan,
                matcher.start(),
                matcher.end(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            et_url.setText(spannableStr)
        }
    }


}