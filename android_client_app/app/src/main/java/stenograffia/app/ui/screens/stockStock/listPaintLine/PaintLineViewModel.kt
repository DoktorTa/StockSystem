package stenograffia.app.ui.screens.stockStock.listPaintLine

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

@HiltViewModel
class PaintLineViewModel @Inject constructor(
    val useCase: PaintUseCase,
) : ViewModel() {

    val allPaintName: Flow<List<PaintNamesTupleModel>> =
        useCase.getAllPaintNames()

}

