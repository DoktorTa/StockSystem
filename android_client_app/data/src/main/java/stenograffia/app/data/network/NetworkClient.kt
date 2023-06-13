package stenograffia.app.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    companion object {
        private const val baseUrl = Urls.BASE_URL

        fun getRetrofit(): Retrofit {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient()
                .newBuilder()
//                .addInterceptor(AuthInterceptor())
                .addInterceptor(logInterceptor)
                .build()

            return createRetrofit(client)
        }

        private fun createRetrofit(client: OkHttpClient): Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }

        fun getStockApi(retrofit: Retrofit): StockApi {
            return retrofit.create(StockApi::class.java)
        }
    }
}
