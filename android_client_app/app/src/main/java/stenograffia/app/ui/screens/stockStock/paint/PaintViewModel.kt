package stenograffia.app.ui.screens.stockStock.paint

import android.content.ClipData
import android.content.ClipData.Item
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import stenograffia.app.R
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.useCases.StockUseCase
import stenograffia.app.domain.utils.ChangeMsg
import stenograffia.app.utils.IDiffQuantity
import javax.inject.Inject

@HiltViewModel
class PaintViewModel @Inject constructor(
    private val stockUseCase: StockUseCase
) : ViewModel(), IDiffQuantity {

    var infoText: Int = 0

    fun getPaintModelById(paintId: Int) : PaintModel {
        return stockUseCase.getPaintModelById(paintId)!!
    }

    fun getLikenessPaintList(paintModel: PaintModel): Pair<List<PaintModel>, Map<Int, String>>{
        val likenessPaint = paintModel.similarColors

        val paintList: MutableList<PaintModel> = mutableListOf()
        val percentLikenessList: MutableMap<Int, String> = mutableMapOf()

        likenessPaint.forEach {
            val paintModel: PaintModel = stockUseCase.getPaintModelById(it[0])!!
            paintList.add(paintModel)
            percentLikenessList[paintModel.id] = it[1].toString()
        }

        return Pair(paintList, percentLikenessList)

    }

    fun copyPaintInfo() {
        ClipData.newPlainText("CYBER ANNA", "TEXT")
    }

    override fun changeQuantity(id: Int, difference: Int) {
        viewModelScope.launch {
            val answer = stockUseCase.updateQuantityById(id, difference)
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
