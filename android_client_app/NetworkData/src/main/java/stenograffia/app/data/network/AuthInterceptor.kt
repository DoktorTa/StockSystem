package stenograffia.app.data.network

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import stenograffia.app.data.DataStoreToken
import stenograffia.app.domain.model.AuthTokens

class AuthInterceptor : Interceptor {

    companion object {
        private const val authHeader = "Authorization"
        private const val scheme = "Bearer "
        private lateinit var token: AuthTokens

        @SuppressLint("StaticFieldLeak")
        private lateinit var tokenManager: DataStoreToken
    }

    fun setTokenManager(tokenManagerNew: DataStoreToken){
        tokenManager = tokenManagerNew
    }

    private fun setTokens(){
        token = runBlocking {
            tokenManager.getTokens().first()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        setTokens()

        val originalRequest = chain.request()
        val builder = originalRequest
            .newBuilder()
            .header(authHeader, scheme + token.accessToken)
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}