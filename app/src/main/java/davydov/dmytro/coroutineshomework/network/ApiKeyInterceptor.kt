package davydov.dmytro.coroutineshomework.network

import davydov.dmytro.coroutineshomework.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey = chain
            .request()
            .apply {
                val urlWithApiKey = url()
                    .newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build()

                newBuilder()
                    .url(urlWithApiKey)
                    .build()
            }

        return chain.proceed(requestWithApiKey)
    }
}