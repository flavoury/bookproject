package com.favour.e_libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URLEncoder

class viewpdf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpdf)

        val pdfview = findViewById<WebView>(R.id.webView)
        val url = intent.getStringExtra("pdfurl").toString()

        pdfview.webViewClient = WebViewClient()
        pdfview.settings.javaScriptEnabled = true
        pdfview.settings.setSupportZoom(true)
        pdfview.loadUrl("https://docs.google.com/gview?embedded=true&url="+ URLEncoder.encode(url, "ISO-8859-1"))

    }
}