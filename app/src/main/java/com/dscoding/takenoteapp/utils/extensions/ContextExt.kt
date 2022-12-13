package com.dscoding.takenoteapp.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.Constants.GOOGLE_PLAY_APP_URL
import com.dscoding.takenoteapp.common.Constants.PRIVACY_POLICY_URL
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

fun Context.openGooglePlayAppPage() {
    Firebase.analytics.logRateApp()
    openUrl(GOOGLE_PLAY_APP_URL)
}

fun Context.openPrivacyPolicyPage() {
    Firebase.analytics.logOpenPrivacyPolicyPage()
    openUrl(PRIVACY_POLICY_URL)
}

fun Context.openUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
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

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}