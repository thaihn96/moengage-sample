package com.moengage.sample.kotlin.callbacks.push

import com.google.firebase.messaging.RemoteMessage
import com.moengage.firebase.listener.FirebaseEventListener
import timber.log.Timber

/**
 * @author Umang Chamaria
 * Date: 2020/08/28
 */
class FcmEventListener : FirebaseEventListener() {
    override fun onNonMoEngageMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("TTT FcmEventListener onNonMoEngageMessageReceived(): $remoteMessage")
        // payload received, add your processing logic here
    }

    override fun onTokenAvailable(token: String) {
        Timber.d("TTT onTokenAvailable FcmEventListener onTokenAvailable(): Token Callback Received. Token: $token")
        // push token received, add your processing logic here
    }
}