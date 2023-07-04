package stenograffia.app.data.network

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stenograffia.app.data.DataStoreToken


class NetworkClient {

    companion object {
        private const val baseUrl = Urls.BASE_URL

        fun getAuthApi(): AuthApi {
            return getAuthRetrofit().create(AuthApi::class.java)
        }

        private fun getAuthRetrofit() : Retrofit {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(logInterceptor)
                .build()

            return createRetrofit(client)
        }

        fun getMainRetrofit(tokenManager: DataStoreToken) : Retrofit {
            setTokens(tokenManager)

            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(logInterceptor)
                .build()

            return createRetrofit(client)
        }

        private fun createRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun setTokens(tokenManager: DataStoreToken){
            AuthInterceptor().setTokenManager(tokenManager)
        }

        fun getStockApi(retrofit: Retrofit): StockApi {
            return retrofit.create(StockApi::class.java)
        }

        fun getUserApi(retrofit: Retrofit): UserApi {
            return retrofit.create(UserApi::class.java)
        }
    }
}
