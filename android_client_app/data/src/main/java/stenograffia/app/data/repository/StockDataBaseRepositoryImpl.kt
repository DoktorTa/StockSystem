package stenograffia.app.data.repository

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
}