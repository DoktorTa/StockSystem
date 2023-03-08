package stenograffia.app.domain.useCases

import kotlinx.coroutines.flow.single
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.repository.IPaintRepository
import javax.inject.Inject

class PaintUseCase @Inject constructor(
    val paintRepository: IPaintRepository
) {
//    var paintList: List<PaintModel> = listOf()

    fun getPaintModelById(paintId: Int): PaintModel? {
        return paintRepository.getPaintById(paintId)
    }

    fun getLinePaint(nameCreator: String, nameLine: String): List<PaintModel>{
        return paintRepository.getPaintsListByCreatorAndLine(nameCreator, nameLine)
    }

}