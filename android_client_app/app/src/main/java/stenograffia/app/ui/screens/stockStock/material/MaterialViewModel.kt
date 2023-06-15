package stenograffia.app.ui.screens.stockStock.material

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.R
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.useCases.StockUseCase
import stenograffia.app.domain.utils.ChangeMsg
import javax.inject.Inject

@HiltViewModel
class MaterialViewModel @Inject constructor(
    private val stockUseCase: StockUseCase,
) : ViewModel() {

    var infoText: Int = 0

    fun getMaterial(materialId: Int): MaterialModel {
        return stockUseCase.getMaterialById(materialId)
    }

    fun getLocation(): Array<String> {
        return stockUseCase.getLocations().toTypedArray()
    }

    fun changeLocationMaterial(materialId: Int, newLocation: String) {
        viewModelScope.launch {
            val answer = stockUseCase.changeLocationMaterial(materialId, newLocation)
            infoText = when (answer) {
                ChangeMsg.CorrectChange() -> R.string.correct_change
                ChangeMsg.ErrorChange() -> R.string.not_correct_change
                ChangeMsg.ErrorConnect() -> R.string.server_disconnect
                else -> {
                    R.string.server_disconnect
                }
            }
        }
    }
}