package stenograffia.app.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import stenograffia.app.vw.PaintViewModel
import stenograffia.app.data.di.PaintRepositoryModel
import stenograffia.app.domain.useCases.PaintUseCase


//@InstallIn(ActivityComponent::class)
//@Component(modules = [PaintRepositoryModel::class])
//interface StockComponent {
//
////    @Component.Builder
////    interface Builder{
////
////        @BindsInstance
////        fun context(context: Context): Builder
////
////        fun build(): StockComponent
////    }
//    fun getPaintUseCase(): PaintUseCase
//
//    fun getPaintViewModel(): PaintViewModel
//}

