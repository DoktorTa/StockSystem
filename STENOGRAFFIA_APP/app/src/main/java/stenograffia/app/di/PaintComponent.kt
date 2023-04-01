package stenograffia.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import stenograffia.app.vw.PaintViewModel
import stenograffia.app.data.di.PaintRepositoryModel
import stenograffia.app.domain.useCases.PaintUseCase
import stenograffia.app.ui.stock.listPaint.PaintListViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [PaintRepositoryModel::class, AppViewModelModule::class])
interface PaintComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): PaintComponent
    }

//    fun getPaintCreatorViewModelFactory(): StockViewModelFactory

    fun getViewModelFactory(): ViewModelFactory


    fun getPaintUseCase(): PaintUseCase

    fun getPaintViewModel(): PaintViewModel

//    fun getPaintCreatorViewModel(): PaintCreatorViewModel

    fun getPaintListViewModel(): PaintListViewModel

}

