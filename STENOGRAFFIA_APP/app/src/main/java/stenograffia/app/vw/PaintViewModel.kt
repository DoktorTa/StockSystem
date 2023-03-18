package stenograffia.app.vw

import android.util.Log
import androidx.lifecycle.ViewModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

class PaintViewModel @Inject constructor(
    val useCase: PaintUseCase
): ViewModel() {

    lateinit var paintModel: PaintModel

    fun loadPaintModelById(paintId: Int) {
        paintModel = useCase.getPaintModelById(paintId)!!
    }

    fun changeQuantityPaintInStock(difference: Int){
        useCase.changeQuantityPaintInStock(paintModel, difference)
    }

}