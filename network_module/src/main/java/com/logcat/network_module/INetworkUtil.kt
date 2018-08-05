package com.logcat.almarker.network_module

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Ben on 31/7/2018.
 */
interface INetworkUtil<REQUESTMETHOD, REQUESTMODEL: RequestModel<*, *>> {
    //config related
    fun getDomain(): String = "http://almarker-benchankuentang181319.codeanyapp.com/api/"

    fun getTimeout(): Int = 10 * 1000

    fun getCommonHeader(): Map<String, String>? {
        //TODO: common header
        return null
    }

    //request related
    fun getRequestMethod(request: REQUESTMODEL): REQUESTMETHOD

    fun <DATAMODEL> request(request: REQUESTMODEL) {
        processRequest<DATAMODEL>(request)
    }

    fun <DATAMODEL> processRequest(request: REQUESTMODEL)

    fun cancelRequest()

    //response related
    fun <DATAMODEL> getResponseModel(jsonString: String?): ResponseModel<DATAMODEL>? {
        if (jsonString == null) return null

        return try {
            val gson = Gson()
            val collectionType = object : TypeToken<ResponseModel<DATAMODEL>>() {}.type
            gson.fromJson(jsonString, collectionType) as ResponseModel<DATAMODEL>
        } catch (e: Exception) {
            null
        }
    }

    fun isSuccess(statusCode:Int): Boolean = when (statusCode) {
        200 -> true
        else -> false
    }
}