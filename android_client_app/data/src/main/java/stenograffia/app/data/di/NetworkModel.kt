package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import stenograffia.app.data.network.NetworkClient
import stenograffia.app.data.network.ServerAPI
import stenograffia.app.data.repository.UserRepositoryImpl
import stenograffia.app.domain.repository.IUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModel {

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {
        return NetworkClient.getHttpClient()
    }

    @Singleton
    @Provides
    fun provideServerAPI(client: OkHttpClient) : ServerAPI {
        return NetworkClient.getServerAPI(client)
    }

    @Singleton
    @Provides
    fun provideUserRepository(serverAPI: ServerAPI): IUserRepository {
        return UserRepositoryImpl(serverAPI)
    }
}