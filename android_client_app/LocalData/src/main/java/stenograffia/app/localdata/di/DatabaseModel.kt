package stenograffia.app.localdata.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import stenograffia.app.localdata.database.DataBase
import stenograffia.app.localdata.database.dao.ObjectsDao
import stenograffia.app.localdata.database.dao.StockDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModel {

    @Singleton
    @Provides
    fun provideStockDao(database: DataBase): StockDao {
        return database.stockDao()
    }

    @Singleton
    @Provides
    fun provideObjectsDao(database: DataBase) : ObjectsDao {
        return database.objectsDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DataBase {
        return DataBase.buildDataBase(context)
    }
}