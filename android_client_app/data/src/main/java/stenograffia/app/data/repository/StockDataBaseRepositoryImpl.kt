package stenograffia.app.data.repository

import stenograffia.app.data.database.PaintDao
import stenograffia.app.data.database.fromPaintEntity
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IStockDataBaseRepository
import javax.inject.Inject

class StockDataBaseRepositoryImpl @Inject constructor(
    private val stockDao: PaintDao
) : IStockDataBaseRepository{

    override suspend fun updatePaint(paintModel: PaintModel) {
        stockDao.updatePaint(paintModel.fromPaintEntity()!!)
    }
}