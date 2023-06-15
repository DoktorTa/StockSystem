package stenograffia.app.ui.screens.stockStock.material

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.R
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.useCases.ChangeLocationMaterialMsg
import stenograffia.app.domain.useCases.StockUseCase
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
            when (answer) {
                ChangeLocationMaterialMsg.CorrectChange() -> infoText = R.string.correct_change
                ChangeLocationMaterialMsg.ErrorChange() -> infoText = R.string.not_correct_change
                ChangeLocationMaterialMsg.ErrorConnect() -> infoText = R.string.server_disconnect
                else -> {infoText = R.string.server_disconnect}
            }
        }
    }
}