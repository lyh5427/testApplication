package com.example.testapplication.service

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class Accesibility : AccessibilityService() {
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {
        Log.d("연결 완료", "213123")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}