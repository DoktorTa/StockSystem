package stenograffia.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import stenograffia.app.data.database.model.PaintEntity
import stenograffia.app.data.database.model.PaintNamesTuple

@Dao
interface StockDao {
    @Query("SELECT * FROM PaintEntity WHERE id = :paintId ")
    fun getPaintById(paintId: Int): PaintEntity

    @Query("SELECT * FROM PaintEntity WHERE nameCreator = :nameCreator AND nameLine = :nameLine")
    fun getListPaintsByLineAndCreator(
        nameCreator: String, nameLine: String): Flow<List<PaintEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPaint(paintEntity: List<PaintEntity>)

    @Update
    fun updateAllPaints(paintEntity: List<PaintEntity>)

    @Query("SELECT MAX(timeLabel) FROM PaintEntity")
    fun getMaxTimeLabel(): Int?

    @Query("SELECT DISTINCT nameCreator, NameLine FROM PaintEntity")
    fun getAllPaintNames(): Flow<List<PaintNamesTuple>>
}