package stenograffia.app.ui.screens.stockStock.listPaint

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

@HiltViewModel
class PaintListViewModel @Inject constructor(
    val useCase: PaintUseCase
) : ViewModel() {

    var paintList: Flow<List<PaintModel>> = flow { listOf<PaintModel>() }
    private var paintNameModelNow: PaintNamesTupleModel? = null

    fun loadPaintList(paintNameModel: PaintNamesTupleModel) {
        if (paintNameModel != paintNameModelNow) {
            paintList = useCase.getLinePaint(paintNameModel)
        }
    }

}
