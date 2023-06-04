# from typing import List
#
# from stock.models.paint import Paint
#
#
import logging

from stock.db.stock_dao import StockDao


class RepositoryStock:
    stock_dao: StockDao

    def __init__(self):
        self.stock_dao = StockDao()

#     def __init__(self):
#         paint = Paint()
#         paint.paint_id = 110000
#         paint.paint_type = "CANS"
#         paint.nameCreator = "MONTANA CANS"
#         paint.nameLine = "BLACK - 400ml"
#         paint.codePaint = "BLK-1005-400"
#         paint.nameColor = "Smash137´s Potato"
#         paint.descriptionColor = " "
#         paint.color= 0xf4e593
#         paint.quantityInStorage = 0
#         paint.placesOfPossibleAvailability = []
#         paint.similarColors = []
#         paint.possibleToBuy = False
#
#         self.all_paint.append(paint)
#
#     def get_all_paint(self) -> List[Paint]:
#         return self.all_paint
#
#     def get_paint_by_id(self, paint_id: int) -> Paint:
#         answer = None
#
#         for paint in self.all_paint:
#             if paint.paint_id == paint_id:
#                 answer = paint
#
#         return answer

