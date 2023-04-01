package stenograffia.app.ui.stock.listPaint

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

class PaintListViewModel @Inject constructor(
    val useCase: PaintUseCase
): ViewModel() {

    var paintList: Flow<List<PaintModel>>? = null

    fun loadPaintList(paintNameModel: PaintNamesTupleModel) {
        paintList = useCase.getLinePaint(paintNameModel)
    }

}