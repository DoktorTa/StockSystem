package stenograffia.app.domain.useCases

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.repository.IPaintRepository
import javax.inject.Inject

class PaintUseCase @Inject constructor(
    val paintRepository: IPaintRepository
) {
//    var paintList: List<PaintModel> = listOf()

    fun getPaintModelById(paintId: Int): PaintModel? {
        return paintRepository.getPaintById(paintId)
    }

    fun getLinePaint(paintNameModel: PaintNamesTupleModel): Flow<List<PaintModel>> {
        return paintRepository.getPaintsListByCreatorAndLine(paintNameModel.nameCreator, paintNameModel.nameLine)
    }

    fun changeQuantityPaintInStock(paintModel: PaintModel, difference: Int){
        paintModel.quantityInStorage += difference
        paintRepository.updatePaint(paintModel)
    }

    fun getAllPaintNames(): Flow<List<PaintNamesTupleModel>>{
        return paintRepository.getAllPaintNames()
    }
}