package stenograffia.app.data.di

import dagger.Module
import dagger.Provides
import stenograffia.app.data.database.PaintDao
import stenograffia.app.data.repository.PaintRepositoryImpl
import stenograffia.app.domain.repository.IPaintRepository
import javax.inject.Singleton

@Module(includes = [DatabaseModel::class])
class PaintRepositoryModel {
    @Singleton
    @Provides
    fun providePaintRepository(paintDao: PaintDao): IPaintRepository {
        return PaintRepositoryImpl(paintDao)
    }
}