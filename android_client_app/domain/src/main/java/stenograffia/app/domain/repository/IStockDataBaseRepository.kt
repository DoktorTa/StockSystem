package stenograffia.app.domain.repository

import kotlinx.coroutines.flow.Flow
import stenograffia.app.domain.model.MaterialModel
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel

interface IStockDataBaseRepository {

    suspend fun updateAllPaint(paintModel: List<PaintModel>)

    suspend fun getMaxTimeLabel(): Int

    fun getPaintById(paintId: Int): PaintModel?

    // TODO: Перейти на PaintNameTupleModel
    fun getPaintsListByCreatorAndLine(nameCreator: String, nameLine: String): Flow<List<PaintModel>>

    fun getAllPaintNames(): Flow<List<PaintNamesTupleModel>>

    fun getAllMaterials(): Flow<List<MaterialModel>>

    fun getMaterialById(materialId: Int): MaterialModel

    fun addAllMaterials(materialModel: List<MaterialModel>)

    fun getMaxMaterialTimeLabel(): Int

    fun getLocations() : MutableSet<String>
}