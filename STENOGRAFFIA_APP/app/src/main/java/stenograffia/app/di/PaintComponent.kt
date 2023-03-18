package stenograffia.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import stenograffia.app.vw.PaintViewModel
import stenograffia.app.data.di.PaintRepositoryModel
import stenograffia.app.domain.useCases.PaintUseCase
import stenograffia.app.vw.PaintCreatorViewModel
import stenograffia.app.vw.PaintListViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [PaintRepositoryModel::class])
interface PaintComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): PaintComponent
    }

    fun getPaintUseCase(): PaintUseCase

    fun getPaintViewModel(): PaintViewModel

    fun getPaintCreatorViewModel(): PaintCreatorViewModel

    fun getPaintListViewModel(): PaintListViewModel
}
