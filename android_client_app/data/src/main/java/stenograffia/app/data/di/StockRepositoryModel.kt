package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stenograffia.app.data.database.PaintDao
import stenograffia.app.data.network.ServerAPI
import stenograffia.app.data.repository.StockNetworkRepositoryImpl
import stenograffia.app.data.repository.PaintRepositoryImpl
import stenograffia.app.data.repository.StockDataBaseRepositoryImpl
import stenograffia.app.domain.repository.IPaintRepository
import stenograffia.app.domain.repository.IStockDataBaseRepository
import stenograffia.app.domain.repository.IStockNetworkRepository
import javax.inject.Singleton


@Module(includes = [DatabaseModel::class, NetworkModel::class])
@InstallIn(SingletonComponent::class)
class StockRepositoryModel {
    @Singleton
    @Provides
    fun provideStockNetworkRepository(serverAPI: ServerAPI): IStockNetworkRepository {
        return StockNetworkRepositoryImpl(serverAPI)
    }

    @Singleton
    @Provides
    fun provideStockDataBaseRepository(paintDao: PaintDao): IStockDataBaseRepository {
        return StockDataBaseRepositoryImpl(paintDao)
    }


    @Singleton
    @Provides
    fun providePaintRepository(paintDao: PaintDao): IPaintRepository {
        return PaintRepositoryImpl(paintDao)
    }
}