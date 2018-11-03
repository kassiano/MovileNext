package com.example.office.movilenext_day2_push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    companion object {
       const val TAG = "FCMService"
    }



    override fun onMessageReceived(remoteMessaging: RemoteMessage) {
        //super.onMessageReceived(remoteMessaging)

        val notification = remoteMessaging.notification
        Log.i(TAG, "FCM Message ID: ${ remoteMessaging.messageId}")
        Log.i(TAG, "FCM Data Message : ${ remoteMessaging.data}")
        Log.i(TAG, "FCM Notifiction Message : ${ notification}")


        notification?.let {

            val title = it.title ?: ""
            val body = it.body?: ""

            Log.i(TAG, "FMC Notification Title: $title")
            Log.i(TAG, "FMC Notification Body: $body")

            NotificationCreation.create(this, title, body)
        }


    }


    override fun onNewToken(token:String?){

      //  Log.i(TAG, token)

        FirebaseMessaging.getInstance().subscribeToTopic("MAIN")


    }

}