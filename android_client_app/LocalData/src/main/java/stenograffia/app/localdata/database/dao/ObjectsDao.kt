package stenograffia.app.localdata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import stenograffia.app.localdata.database.model.ObjectEntity

@Dao
interface ObjectsDao {

    @Query("SELECT * FROM ObjectEntity")
    fun getAllObjects(): Flow<List<ObjectEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllObjects(objectsEntity: List<ObjectEntity>)

    @Query("SELECT DISTINCT(objectName) FROM ObjectEntity")
    fun getLocations(): List<String>
}