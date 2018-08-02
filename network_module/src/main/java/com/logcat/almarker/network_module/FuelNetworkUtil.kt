package com.logcat.almarker.network_module

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method

/**
 * Created by Ben on 31/7/2018.
 */
class FuelNetworkUtil : INetworkUtil<Method, FuelRequestModel> {
    private val manager = FuelManager()

    init {
        manager.basePath = getDomain()
        manager.baseHeaders = getCommonHeader()
    }

    override fun setTimeout(): Int = 10 * 1000

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

    override fun processRequest(request: FuelRequestModel) {
        val fuelRequest = manager.request(getRequestMethod(request), request.requestUrl, request.getParsedParameter())

        request.getParsedBody()?.let {
            fuelRequest.body(it)
        }

        request.header?.forEach {
            fuelRequest.header(it)
        }

        fuelRequest.responseString { request, response, result ->
            //TODO: handle callback
        }
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}