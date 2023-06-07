package stenograffia.app.ui.screens.stockStock.paint

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

@HiltViewModel
class PaintViewModel @Inject constructor(
    val useCase: PaintUseCase
) : ViewModel() {

    lateinit var paintModel: PaintModel

    fun loadPaintModelById(paintId: Int) {
        paintModel = useCase.getPaintModelById(paintId)!!
    }

    fun changeQuantityPaintInStock(difference: Int) {
        useCase.changeQuantityPaintInStock(paintModel, difference)
    }

    fun getLikenessPaintList(): Pair<List<PaintModel>, Map<Int, String>>{
        val likenessPaint = paintModel.similarColors

        val paintList: MutableList<PaintModel> = mutableListOf()
        val percentLikenessList: MutableMap<Int, String> = mutableMapOf()

        likenessPaint.forEach {
            val paintModel: PaintModel = useCase.getPaintModelById(it[0])!!
            paintList.add(paintModel)
            percentLikenessList[paintModel.id] = it[1].toString()
        }

        return Pair(paintList, percentLikenessList)

    }

}