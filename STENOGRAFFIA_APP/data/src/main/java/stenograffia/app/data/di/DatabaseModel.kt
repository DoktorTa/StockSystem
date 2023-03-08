package stenograffia.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import stenograffia.app.data.database.DataBase
import stenograffia.app.data.database.PaintDao
import javax.inject.Singleton

@Module
class DatabaseModel {

    @Singleton
    @Provides
    fun providePaintDao(database: DataBase): PaintDao {
        return database.paintDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): DataBase {
        return DataBase.buildDataBase(context)
    }
}