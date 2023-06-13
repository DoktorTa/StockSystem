package stenograffia.app.ui.screens.stockStock.listPaintLine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.StockUseCase
import javax.inject.Inject

@HiltViewModel
class PaintLineViewModel @Inject constructor(
    private val stockUseCase: StockUseCase,
) : ViewModel() {

    val allPaintName: Flow<List<PaintNamesTupleModel>> = getAllPaintNames()

    private fun getAllPaintNames(): Flow<List<PaintNamesTupleModel>> {
        viewModelScope.launch { stockUseCase.updatePaintsByTime() }
        return stockUseCase.getAllPaintNames()
    }

}

