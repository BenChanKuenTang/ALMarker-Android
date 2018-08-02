package com.logcat.almarker.network_module

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ben on 31/7/2018.
 */

typealias successCallback = (String) -> Unit
typealias errorCallback = (String) -> Unit

abstract class RequestModel<PARAMETER, BODY>(
        val requestMethod: RequestMethod,
        val requestUrl: String,
        private val parameter: Any? = null,
        private val body: Any? = null,
        val header: List<Map<String, String>>? = null,
        val successCallback: successCallback,
        val errorCallback: errorCallback
) {
    fun getParsedParameter(): PARAMETER? {
        return parameter?.let {
            val gson = Gson()
            val jsonString = gson.toJson(it)
            val collectionType = object : TypeToken<PARAMETER>() {}.type
            gson.fromJson(jsonString, collectionType) as PARAMETER
        }
    }

    fun getParsedBody(): BODY? {
        return body?.let {
            val gson = Gson()
            val jsonString = gson.toJson(this)
            val collectionType = object : TypeToken<BODY>() {}.type
            gson.fromJson(jsonString, collectionType) as BODY
        }
    }
}