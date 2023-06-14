package stenograffia.app.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stenograffia.app.data.database.*
import stenograffia.app.data.database.model.*
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.repository.IStockDataBaseRepository
import javax.inject.Inject

class StockDataBaseRepositoryImpl @Inject constructor(
    private val stockDao: StockDao
) : IStockDataBaseRepository{


    override suspend fun updateAllPaint(paintModel: List<PaintModel>) {
        stockDao.addAllPaint(paintModel.map { it.fromPaintEntity()!! })
    }

    override suspend fun getMaxTimeLabel(): Int {
        return stockDao.getMaxPaintTimeLabel() ?: 0
    }

    override fun getPaintById(paintId: Int): PaintModel? {
        return stockDao.getPaintById(paintId).toPaintModel()
    }

    override fun getPaintsListByCreatorAndLine(nameCreator: String, nameLine: String):
            Flow<List<PaintModel>> {
        return stockDao.getListPaintsByLineAndCreator(nameCreator, nameLine).map {
            it.map{paintItem ->
                paintItem.toPaintModel()!!
            }
        }
    }

    override fun getAllPaintNames(): Flow<List<PaintNamesTupleModel>> {
        return stockDao.getAllPaintNames().map { it.map { paintNamesTupleModel ->
            paintNamesTupleModel.toPaintNamesTupleModel()
        } }
    }

    override fun getAllMaterials(): Flow<List<MaterialModel>> {
        return stockDao.getAllMaterials().map { it.map { it.toMaterialModel() } }
    }

    override fun getMaterialById(materialId: Int): MaterialModel {
        return stockDao.getMaterialById(materialId).toMaterialModel()
    }

    override fun addAllMaterials(materialModel: List<MaterialModel>) {
        stockDao.addAllMaterials(materialModel.map { it.fromMaterialEntity() })
    }

    override fun getMaxMaterialTimeLabel(): Int {
        return stockDao.getMaxMaterialTimeLabel() ?: 0
    }


//    fun getAllMaterials(): Flow<List<MaterialEntity>>
//
//    fun getMaterialById(materialId: Int): MaterialEntity
//
//    fun addAllMaterials(materialEntity: List<MaterialEntity>)
//
//    fun getMaxMaterialTimeLabel(): Int?
}