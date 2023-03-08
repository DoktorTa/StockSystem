package stenograffia.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import stenograffia.app.PaintViewModel
import stenograffia.app.data.di.PaintRepositoryModel
import stenograffia.app.domain.useCases.PaintUseCase
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
}
