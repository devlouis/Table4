package com.studio.hub.table4app.app.ui.utils

import android.util.Log
import com.studio.hub.table4app.BuildConfig

/**
 * Created by louislopez on 10,December,2018
 * MDP Consulting,
 * Peru, Lima.
 */
class LogUtils {

    private val LOG_PREFIX = "LetsGo: "
    private val LOG_PREFIX_LENGTH = LOG_PREFIX.length
    private val MAX_LOG_TAG_LENGTH = 23

    fun makeLogTag(str: String): String {
        return if (str.length > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1)
        } else LOG_PREFIX + str

    }

    fun makeLogTag(cls: Class<*>): String {
        return makeLogTag(cls.simpleName)
    }

    fun v(tag: String, message: String) {
        val nTag = makeLogTag(tag)

        if (BuildConfig.DEBUG || false || Log.isLoggable(nTag, Log.DEBUG)) {
            Log.v(nTag, message)
        }
    }

    fun LOGI(tag: String, message: String, cause: Throwable) {
        val nTag = makeLogTag(tag)

        if (BuildConfig.DEBUG || false || Log.isLoggable(nTag, Log.DEBUG)) {
            Log.i(nTag, message, cause)
        }
    }
}