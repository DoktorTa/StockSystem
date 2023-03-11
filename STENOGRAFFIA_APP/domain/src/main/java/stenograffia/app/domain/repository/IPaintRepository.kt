package stenograffia.app.domain.repository

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.PaintModel

interface IPaintRepository {

    fun getPaintById(paintId: Int): PaintModel?

    fun getPaintsListByCreatorAndLine(nameCreator: String, nameLine: String): List<PaintModel>

    fun updatePaint(paintModel: PaintModel)
}