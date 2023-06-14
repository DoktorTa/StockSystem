package stenograffia.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import stenograffia.app.data.database.model.MaterialEntity
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
    fun getMaxPaintTimeLabel(): Int?

    @Query("SELECT DISTINCT nameCreator, NameLine FROM PaintEntity")
    fun getAllPaintNames(): Flow<List<PaintNamesTuple>>

    @Query("SELECT * FROM MaterialEntity")
    fun getAllMaterials(): Flow<List<MaterialEntity>>

    @Query("SELECT * FROM MaterialEntity WHERE id = :materialId")
    fun getMaterialById(materialId: Int): MaterialEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllMaterials(materialEntity: List<MaterialEntity>)

    @Query("SELECT MAX(timeLabel) FROM MaterialEntity")
    fun getMaxMaterialTimeLabel(): Int?
}