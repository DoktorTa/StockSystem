package stenograffia.app.ui.stock.listPaintLine

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import stenograffia.app.di.ViewModelAssistedFactory
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.PaintUseCase

class PaintLineViewModel @AssistedInject constructor(
    val useCase: PaintUseCase,
): ViewModel(){

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<PaintLineViewModel>

    val allPaintName: Flow<List<PaintNamesTupleModel>> =
        useCase.getAllPaintNames()

}

