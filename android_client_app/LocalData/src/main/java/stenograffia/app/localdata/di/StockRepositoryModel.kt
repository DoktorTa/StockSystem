package stenograffia.app.localdata.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stenograffia.app.domain.repository.IObjectDataBaseRepository
import stenograffia.app.domain.repository.IStockDataBaseRepository
import stenograffia.app.localdata.database.dao.ObjectsDao
import stenograffia.app.localdata.database.dao.StockDao
import stenograffia.app.localdata.repository.ObjectsDataBaseRepositoryImpl
import stenograffia.app.localdata.repository.StockDataBaseRepositoryImpl
import javax.inject.Singleton

@Module(includes = [DatabaseModel::class])
@InstallIn(SingletonComponent::class)
class StockRepositoryModel {
    @Singleton
    @Provides
    fun provideStockDataBaseRepository(paintDao: StockDao): IStockDataBaseRepository {
        return StockDataBaseRepositoryImpl(paintDao)
    }

    @Singleton
    @Provides
    fun provideObjectDataBaseRepository(objectsDao: ObjectsDao) : IObjectDataBaseRepository {
        return ObjectsDataBaseRepositoryImpl(objectsDao)
    }
}