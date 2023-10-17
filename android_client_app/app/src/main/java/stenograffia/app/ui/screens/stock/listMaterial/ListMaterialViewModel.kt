package stenograffia.app.ui.screens.stock.listMaterial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.useCases.StockUseCase
import javax.inject.Inject

@HiltViewModel
class ListMaterialViewModel @Inject constructor(
    private val stockUseCase: StockUseCase
) : ViewModel() {

    var materialList: Flow<List<MaterialModel>> = loadMaterials()

    private fun loadMaterials() : Flow<List<MaterialModel>> {
        viewModelScope.launch { stockUseCase.updateMaterialsByTime() }
        return stockUseCase.getMaterials()
    }
}