package stenograffia.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import stenograffia.app.data.database.model.PaintEntity

@Database(entities = [PaintEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        private const val DATABASE_NAME = "STOCK"

        fun buildDataBase(context: Context): DataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries()
                .build()
        }
    }

}