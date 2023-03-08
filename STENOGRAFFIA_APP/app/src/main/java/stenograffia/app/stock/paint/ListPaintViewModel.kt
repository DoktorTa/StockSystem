package stenograffia.app.stock.paint

import stenograffia.app.domain.model.PaintModel
import stenograffia.app.domain.model.TypePaint

class ListPaintViewModel {

    var paintList: List<PaintModel> = mutableListOf()

    init {
//        val p1 = PaintModel(1, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1005-400", "SMASH137´S POTATO", " ", 0xF4E593, 12, listOf(), listOf(), false)
//        val p2 = PaintModel(2, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1010-400", "EASTER YELLOW", " ", 0xFFE688, 18, listOf(), listOf(), false)
//        val p3 = PaintModel(3, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1025-400", "KICKING YELLOW", " ", 0xFFDA15, 2, listOf(), listOf(), false)
//        val p4 = PaintModel(4, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1030-400", "YELLOW", " ", 0xFCC000, 0, listOf(), listOf(), true)
//        val p5 = PaintModel(5, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1045-400", "MELON YELLOW", " ", 0xF59E01, 0, listOf(), listOf(), false)
    //        paintList = listOf(p1, p2, p3, p4, p5)
    }

    fun getPaintById(id: Int): PaintModel?{
        return paintList.filter { it.id == id }[0]
    }
}