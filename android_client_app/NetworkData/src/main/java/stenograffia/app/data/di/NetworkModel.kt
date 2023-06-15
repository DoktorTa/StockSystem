package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import stenograffia.app.data.network.AuthApi
import stenograffia.app.data.network.NetworkClient
import stenograffia.app.data.network.StockApi
import stenograffia.app.data.repository.UserRepositoryImpl
import stenograffia.app.domain.repository.IUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModel {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return NetworkClient.getRetrofit()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return NetworkClient.getAuthApi(retrofit)
    }

    @Singleton
    @Provides
    fun provideStockApi(retrofit: Retrofit) : StockApi {
        return NetworkClient.getStockApi(retrofit)
    }


    @Singleton
    @Provides
    fun provideUserRepository(authApi: AuthApi): IUserRepository {
        return UserRepositoryImpl(authApi)
    }
}