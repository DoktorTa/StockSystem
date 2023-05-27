package stenograffia.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
//import stenograffia.app.di.StockComponent


@HiltAndroidApp
class App : Application() {

//    lateinit var paintComponent: PaintComponent
//        private set

//    companion object {
//        var mInstance: App? = null
//
//        fun getInstance(): App? = mInstance
//
//        fun getContext(): Context? = mInstance?.applicationContext
//    }

//    override fun onCreate() {
//        super.onCreate()
//        paintComponent = DaggerPaintComponent
//            .builder()
//            .context(context = this)
//            .build()
//
//        mInstance = this
//    }

//    val stockComponent: StockComponent by lazy {
//        initializeComponent()
//    }
//
//    open fun initializeComponent(): StockComponent {
//        return DaggerStockComponent.factory().create(applicationContext)
//    }
}