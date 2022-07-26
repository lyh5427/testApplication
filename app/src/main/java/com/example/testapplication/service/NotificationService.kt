package com.example.testapplication.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationService : NotificationListenerService() {
    override fun onListenerConnected() {
        super.onListenerConnected()
        //Log.e("kobbi","MyNotificationListener.onListenerConnected()")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        //Log.e("kobbi","MyNotificationListener.onListenerDisconnected()")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        var notification = sbn!!.notification
        var extras = notification.extras
        var title = extras.getString(Notification.EXTRA_TITLE)
        var text = extras.getCharSequence(Notification.EXTRA_TEXT)

        Log.d("Noti Test : ", " $title , $text   134131231")
    }
}