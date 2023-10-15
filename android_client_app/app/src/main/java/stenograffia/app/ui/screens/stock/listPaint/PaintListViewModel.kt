package stenograffia.app.ui.screens.stock.listPaint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.StockUseCase
import javax.inject.Inject

@HiltViewModel
class PaintListViewModel @Inject constructor(
    private val stockUseCase: StockUseCase
) : ViewModel() {

    var paintList: Flow<List<PaintModel>> = flow { listOf<PaintModel>() }
    private var paintNameModelNow: PaintNamesTupleModel? = null

    fun loadPaintList(paintNameModel: PaintNamesTupleModel) {
        viewModelScope.launch { stockUseCase.updatePaintsByTime() }
        if (paintNameModel != paintNameModelNow) {
            paintList = stockUseCase.getLinePaint(paintNameModel)
        }
    }

}
