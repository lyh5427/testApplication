package com.example.testapplication.ui.appwebtest

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.testapplication.R
import com.example.testapplication.ui.webView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class PageTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_test)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val pageTestWebView = findViewById<WebView>(R.id.pageTestWebView)
        val pageTestnav = findViewById<BottomNavigationView>(R.id.pageTestnav)
        val pageTestViewPager = findViewById<ViewPager>(R.id.pageTestViewpager)
        val tab = findViewById<TabLayout>(R.id.tabs)
        pageTestWebView.webViewClient = WebViewClient()
        pageTestWebView.loadUrl("https://www.google.com")

        setViewPager(pageTestViewPager)
        tab.setupWithViewPager(pageTestViewPager)
    }

    private fun setViewPager(viewPager: ViewPager) {
        val a = webView.newInstance("1", "https://www.antiscam.co.kr/terms/used")
        val b = webView.newInstance("1", "https://www.google.com")



        val adapter = PagerTestAdapter(supportFragmentManager)
        adapter.addFrag(a,"")
        adapter.addFrag(b,"")

        viewPager.adapter = adapter

    }
    private class PagerTestAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}