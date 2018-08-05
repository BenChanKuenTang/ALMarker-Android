package com.logcat.almarker.network_module

import android.os.Handler
import android.os.Looper
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method

/**
 * Created by Ben on 31/7/2018.
 *
 * This network util is using Fuel
 * @see <a href="https://github.com/kittinunf/Fuel">https://github.com/kittinunf/Fuel</a>
 */
class FuelNetworkUtil : INetworkUtil<Method, FuelRequestModel> {
    companion object {
        private val manager = FuelManager()
    }

    init {
        manager.basePath = getDomain()
        manager.baseHeaders = getCommonHeader()
        manager.timeoutInMillisecond = getTimeout()
        manager.timeoutReadInMillisecond = getTimeout()
    }

    override fun getRequestMethod(request: FuelRequestModel): Method {
        return when (request.requestMethod) {
            RequestMethod.GET -> Method.GET
            RequestMethod.POST -> Method.POST
            RequestMethod.PUT -> Method.PUT
            RequestMethod.DELETE -> Method.DELETE
            RequestMethod.PATCH -> Method.PATCH
            RequestMethod.HEAD -> Method.HEAD
        }
    }

    override fun <DATAMODEL> processRequest(request: FuelRequestModel) {
        val requestMethod = getRequestMethod(request)
        val url = request.requestUrl
        val parameter = request.getParsedParameter()
        val body = request.getParsedBody()
        val headers = request.headers
        val successCallback = request.successCallback
        val failCallback = request.failCallback

        val fuelRequest = manager.request(requestMethod, url, parameter)

        body?.let {
            fuelRequest.body(it)
        }

        headers?.forEach {
            fuelRequest.header(it)
        }

        fuelRequest.responseString { request, response, result ->
            Handler(Looper.getMainLooper()).post {
                val (data, error) = result
                error?.let {
                    failCallback(it.message ?: "")
                } ?: run {
                    val statusCode = response.statusCode
                    val responseObj = getResponseModel<DATAMODEL>(data)

                    if (statusCode == 200) {
                        successCallback(statusCode, responseObj?.data)
                    } else {
                        failCallback(responseObj?.error ?: "Unknown error")
                    }
                }
            }
        }
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}