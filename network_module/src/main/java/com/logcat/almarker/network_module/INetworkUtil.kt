package com.logcat.almarker.network_module

/**
 * Created by Ben on 31/7/2018.
 */
interface INetworkUtil<REQUESTMETHOD, REQUESTMODEL: RequestModel<*, *>> {
    //config related
    fun getDomain(): String = "http://10.0.3.2/php/ALMarker-Server/api/"

    fun setTimeout(): Int

    fun getCommonHeader(): Map<String, String>? {
        //TODO: common header
        return null
    }

    //request related
    fun getRequestMethod(request: REQUESTMODEL): REQUESTMETHOD

    fun request(request: REQUESTMODEL) {
        processRequest(request)
    }

    fun processRequest(request: REQUESTMODEL)

    fun cancelRequest()
}