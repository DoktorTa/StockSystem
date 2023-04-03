package stenograffia.app.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import stenograffia.app.ui.stock.listPaint.PaintListViewModel
//import stenograffia.app.vw.DetailEmailViewModel
import stenograffia.app.ui.stock.listPaintLine.PaintLineViewModel
//
//@Module(
//    includes = [ViewModelFactoryModule::class]
//)
//interface AppViewModelModule {
//
//    @Binds
//    @[IntoMap ViewModelAssistedFactoryKey(PaintLineViewModel::class)]
//    fun bindsPaintLineViewModelFactory(factory: PaintLineViewModel.Factory): ViewModelAssistedFactory<*>
//
//    @Binds
//    @[IntoMap ViewModelAssistedFactoryKey(PaintListViewModel::class)]
//    fun bindsPaintListViewModelFactory(factory: PaintListViewModel.Factory): ViewModelAssistedFactory<*>
//
////    @Binds
////    @[IntoMap ViewModelAssistedFactoryKey(DetailEmailViewModel::class)]
////    fun bindsDetailEmailViewModelFactory(factory: DetailEmailViewModel.Factory): ViewModelAssistedFactory<*>
//}