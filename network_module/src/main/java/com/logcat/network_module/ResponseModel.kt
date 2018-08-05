package com.logcat.almarker.network_module

data class ResponseModel<out MODEL>(
        val data: MODEL? = null,
        val error: String? = null
)