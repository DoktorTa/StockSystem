package stenograffia.app.domain.repository

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel

interface IPaintRepository {

    fun getPaintById(paintId: Int): PaintModel?

    fun getPaintsListByCreatorAndLine(nameCreator: String, nameLine: String): List<PaintModel>

    fun updatePaint(paintModel: PaintModel)

    fun getAllPaintNames(): List<PaintNamesTupleModel>
}