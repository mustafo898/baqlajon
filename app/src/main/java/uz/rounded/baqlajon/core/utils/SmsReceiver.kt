package uz.rounded.baqlajon.core.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsReceiver(
    private val success: (Intent) -> Unit
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(SmsRetriever.SMS_RETRIEVED_ACTION)) {
            val extras = intent?.extras as Bundle
            val smsRetrieverStatus = extras.get(SmsRetriever.EXTRA_STATUS) as Status
            when (smsRetrieverStatus.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val messageIntent =
                        extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                    if (messageIntent != null) {
                        success.invoke(messageIntent)
                    }
                }
            }
        }
    }
}