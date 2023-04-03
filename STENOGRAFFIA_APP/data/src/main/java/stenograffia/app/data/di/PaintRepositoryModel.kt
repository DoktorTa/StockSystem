package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import stenograffia.app.data.database.PaintDao
import stenograffia.app.data.repository.PaintRepositoryImpl
import stenograffia.app.domain.repository.IPaintRepository
import javax.inject.Singleton

@Module(includes = [DatabaseModel::class])
@InstallIn(SingletonComponent::class)
class PaintRepositoryModel {
    @Singleton
    @Provides
    fun providePaintRepository(paintDao: PaintDao): IPaintRepository {
        return PaintRepositoryImpl(paintDao)
    }
}