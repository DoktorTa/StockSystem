package stenograffia.app.localdata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import stenograffia.app.localdata.database.dao.ObjectsDao
import stenograffia.app.localdata.database.dao.StockDao
import stenograffia.app.localdata.database.model.MaterialEntity
import stenograffia.app.localdata.database.model.ObjectEntity
import stenograffia.app.localdata.database.model.PaintEntity

@Database(entities = [PaintEntity::class, MaterialEntity::class, ObjectEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun stockDao(): StockDao
    abstract fun objectsDao(): ObjectsDao

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