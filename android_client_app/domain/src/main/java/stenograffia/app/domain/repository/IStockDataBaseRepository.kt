package stenograffia.app.domain.repository

import stenograffia.app.domain.model.PaintModel

interface IStockDataBaseRepository {

    suspend fun updatePaint(paintModel: PaintModel)
}