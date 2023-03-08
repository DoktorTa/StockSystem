package stenograffia.app

import android.app.Application
import android.content.Context
import stenograffia.app.di.DaggerPaintComponent
import stenograffia.app.di.PaintComponent

class App : Application() {

    lateinit var paintComponent: PaintComponent
        private set

    companion object {
        var mInstance: App? = null

        fun getInstance(): App? = mInstance

        fun getContext(): Context? = mInstance?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        paintComponent = DaggerPaintComponent
            .builder()
            .context(context = this)
            .build()

        mInstance = this
    }
}