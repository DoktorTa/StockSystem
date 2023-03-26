package stenograffia.app.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import stenograffia.app.data.database.*
import stenograffia.app.data.utils.DataGenerate
import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.PaintNamesTupleModel
import stenograffia.app.domain.model.TypePaint
import stenograffia.app.domain.repository.IPaintRepository

class PaintRepositoryImpl(
    val paintDao: PaintDao
): IPaintRepository {

    // Нахуй null
    override fun getPaintById(paintId: Int): PaintModel? {
        return paintDao.getPaintById(paintId).toPaintModel()
    }

    override fun getPaintsListByCreatorAndLine(nameCreator: String, nameLine: String):
            List<PaintModel> {
        return paintDao.getListPaintsByLineAndCreator(nameCreator, nameLine).map {
            it.toPaintModel()!!
        }
    }

    override fun updatePaint(paintModel: PaintModel) {
        paintDao.updatePaint(paintModel.fromPaintEntity()!!)
    }

    override fun getAllPaintNames(): List<PaintNamesTupleModel>{
        return paintDao.getAllPaintNames().map { it.toPaintNamesTupleModel() }
    }


    init {
        val data = dataGenerate()
        data.forEach {
            paintDao.addPaint(it.fromPaintEntity()!!)
        }
    }

    fun dataGenerate(): List<PaintModel>{
        val data: MutableList<PaintModel> = mutableListOf()

        val dataGenerate = DataGenerate()
        data.addAll(dataGenerate.getBlack())
        data.addAll(dataGenerate.getGold())
        data.addAll(dataGenerate.getArton())

        return data
    }

}