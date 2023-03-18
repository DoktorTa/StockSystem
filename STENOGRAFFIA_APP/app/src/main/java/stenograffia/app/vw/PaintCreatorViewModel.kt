package stenograffia.app.vw

import androidx.lifecycle.ViewModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.useCases.PaintUseCase
import javax.inject.Inject

class PaintCreatorViewModel @Inject constructor(
    val useCase: PaintUseCase
): ViewModel()  {

    fun getAllPaintName(): List<PaintNamesTupleModel> {
        return useCase.getAllPaintNames()
    }

}