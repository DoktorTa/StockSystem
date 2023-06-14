package stenograffia.app.ui.screens.stockStock.material

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.useCases.StockUseCase
import javax.inject.Inject

@HiltViewModel
class MaterialViewModel @Inject constructor(
    private val stockUseCase: StockUseCase,
) : ViewModel() {

    fun getMaterial(materialId: Int): MaterialModel {
        return stockUseCase.getMaterialById(materialId)
    }
}