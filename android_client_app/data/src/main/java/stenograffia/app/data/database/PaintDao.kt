package stenograffia.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PaintDao {
    @Query("SELECT * FROM PaintEntity WHERE id = :paintId ")
    fun getPaintById(paintId: Int): PaintEntity?

    @Query("SELECT * FROM PaintEntity WHERE nameCreator = :nameCreator AND nameLine = :nameLine")
    fun getListPaintsByLineAndCreator(nameCreator: String, nameLine: String): Flow<List<PaintEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPaint(paintEntity: PaintEntity)

    @Update
    fun updatePaint(paintEntity: PaintEntity)

    @Query("SELECT DISTINCT nameCreator, NameLine FROM PaintEntity")
    fun getAllPaintNames(): Flow<List<PaintNamesTuple>>
}