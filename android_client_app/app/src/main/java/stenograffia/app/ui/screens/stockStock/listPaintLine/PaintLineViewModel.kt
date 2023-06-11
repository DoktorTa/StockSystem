package stenograffia.app.ui.screens.stockStock.listPaintLine

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.LiveData
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
        Log.d("PaintLineViewModel", "1")
        viewModelScope.launch { stockUseCase.updatePaintsByTime() }
        Log.d("PaintLineViewModel", "12")
        Log.d("PaintLineViewModel", stockUseCase.getPaintModelById(110000).toString())
        return stockUseCase.getAllPaintNames()
    }

}

