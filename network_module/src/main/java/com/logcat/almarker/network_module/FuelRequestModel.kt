package com.logcat.almarker.network_module

class FuelRequestModel(
        requestMethod: RequestMethod,
        requestUrl: String,
        parameter: Any? = null,
        body: Any? = null,
        header: List<Map<String, String>>? = null,
        successCallback: successCallback,
        errorCallback: errorCallback
): RequestModel<List<Pair<String, Any?>>, String>(
        requestMethod, requestUrl, parameter, body, header, successCallback, errorCallback
)