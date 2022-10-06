package com.dscoding.takenoteapp

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.Assert
import java.util.*
import kotlin.concurrent.schedule

fun assertNodeEqualsText(node: SemanticsNodeInteraction, text: String) {
    for ((key, value) in node.fetchSemanticsNode().config) {
        if (key.name == "Text") {
            Assert.assertEquals(
                text, value.toString().replace("[", "").replace("]", "").trim()
            )
        }
    }
}

/*

object AsyncTimer {
    var expired = false
    fun start(delayTime: Long) {
        expired = false
        Timer().schedule(delayTime) {
            expired = true
        }
    }
}

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.delay(delayTime: Long) {
    AsyncTimer.start(delayTime)
    this.waitUntil(
        condition = { AsyncTimer.expired }, timeoutMillis = delayTime + 1000
    )
}

*/

