package com.logcat.almarker.network_module

class FuelRequestModel(
        requestMethod: RequestMethod,
        requestUrl: String,
        parameter: Any? = null,
        body: Any? = null,
        headers: List<Map<String, String>>? = null,
        successCallback: successCallback,
        failCallback: failCallback
): RequestModel<List<Pair<String, Any?>>, String>(
        requestMethod, requestUrl, parameter, body, headers, successCallback, failCallback
)