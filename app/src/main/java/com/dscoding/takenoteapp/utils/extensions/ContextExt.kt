package com.dscoding.takenoteapp.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.utils.Constants.GOOGLE_PLAY_APP_URL
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

fun Context.openGooglePlayAppPage() {
    Firebase.analytics.logRateApp()
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_APP_URL))
    startActivity(browserIntent)
}


fun Context.launchShareAppIntent() {
    Firebase.analytics.logShareApp()
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.settings_share_app_intent_message, GOOGLE_PLAY_APP_URL)
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}