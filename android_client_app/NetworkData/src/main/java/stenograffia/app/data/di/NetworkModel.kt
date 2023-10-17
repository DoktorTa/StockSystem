package stenograffia.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import stenograffia.app.data.network.api.AuthApi
import stenograffia.app.data.network.NetworkClient
import stenograffia.app.data.network.api.StockApi
import stenograffia.app.data.repository.UserNetworkRepositoryImpl
import stenograffia.app.domain.repository.IUserNetworkRepository
import stenograffia.app.data.DataStoreToken
import stenograffia.app.data.network.api.UserApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModel {

    @Singleton
    @Provides
    fun provideDataStoreToken(@ApplicationContext context: Context): DataStoreToken {
        return DataStoreToken(context)
    }

    @Singleton
    @Provides
    fun provideMainRetrofit(tokenManager: DataStoreToken) : Retrofit {
        return NetworkClient.getMainRetrofit(tokenManager)
    }

    @Singleton
    @Provides
    fun provideAuthApi() : AuthApi {
        return NetworkClient.getAuthApi()
    }

    @Singleton
    @Provides
    fun provideUserApi(mainRetrofit: Retrofit) : UserApi {
        return NetworkClient.getUserApi(mainRetrofit)
    }

    @Singleton
    @Provides
    fun provideStockApi(mainRetrofit: Retrofit) : StockApi {
        return NetworkClient.getStockApi(mainRetrofit)
    }

    @Singleton
    @Provides
    fun provideUserRepository(authApi: AuthApi, userApi: UserApi): IUserNetworkRepository {
        return UserNetworkRepositoryImpl(authApi, userApi)
    }

}