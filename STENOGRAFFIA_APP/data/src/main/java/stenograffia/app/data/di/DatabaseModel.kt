package stenograffia.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import stenograffia.app.data.database.DataBase
import stenograffia.app.data.database.PaintDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModel {

    @Singleton
    @Provides
    fun providePaintDao(database: DataBase): PaintDao {
        return database.paintDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DataBase {
        return DataBase.buildDataBase(context)
    }
}