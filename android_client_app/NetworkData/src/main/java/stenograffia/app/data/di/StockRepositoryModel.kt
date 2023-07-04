package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stenograffia.app.data.network.StockApi
import stenograffia.app.data.repository.StockNetworkRepositoryImpl
import stenograffia.app.domain.repository.IStockNetworkRepository
import javax.inject.Singleton


@Module(includes = [NetworkModel::class])
@InstallIn(SingletonComponent::class)
class StockRepositoryModel {
    @Singleton
    @Provides
    fun provideStockNetworkRepository(stockApi: StockApi): IStockNetworkRepository {
        return StockNetworkRepositoryImpl(stockApi)
    }
}