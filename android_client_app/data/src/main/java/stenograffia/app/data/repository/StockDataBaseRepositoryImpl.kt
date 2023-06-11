package stenograffia.app.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stenograffia.app.data.database.*
import stenograffia.app.data.database.model.fromPaintEntity
import stenograffia.app.data.database.model.toPaintModel
import stenograffia.app.data.database.model.toPaintNamesTupleModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.repository.IStockDataBaseRepository
import javax.inject.Inject

class StockDataBaseRepositoryImpl @Inject constructor(
    private val stockDao: StockDao
) : IStockDataBaseRepository{


    override suspend fun updateAllPaint(paintModel: List<PaintModel>) {
        try {
            Log.d("StockDatabaseRepositoryImpl", "s")
            val x = paintModel.map { it.fromPaintEntity()!! }
            Log.d("StockDatabaseRepositoryImpl", x[0].toString())
            stockDao.addAllPaint(paintModel.map { it.fromPaintEntity()!! })
        } catch (e: Exception) {
            Log.d("StockDatabaseRepositoryImpl", e.toString())
        }
    }

    override suspend fun getMaxTimeLabel(): Int {
        return stockDao.getMaxTimeLabel() ?: 0
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
}