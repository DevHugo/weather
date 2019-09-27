package com.weather.datasource.remote.utils

import okhttp3.*

class MockInterceptor(private val responseCode: Int,
                      private val json: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
                .newBuilder()
                .code(responseCode)
                .protocol(Protocol.HTTP_2)
                .message(json)
                .body(ResponseBody.create(
                        MediaType.parse("application/json"),
                    json.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }

}