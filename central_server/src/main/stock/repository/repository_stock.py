import logging

from fastapi import HTTPException
from starlette import status

from stock.db.stock_dao import StockDao


class RepositoryStock:
    stock_dao: StockDao

    def __init__(self):
        self.stock_dao = StockDao()

    def get_paint_by_time(self, time: int):
        return self.stock_dao.get_paint_by_time_label(time)

    def change_quantity_paint(self, paint_id: int, diff_quantity: int, time: int):
        try:
            return self.stock_dao.change_quantity_paint(paint_id, diff_quantity, time)
        except Exception as e:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail='Less zero')

    def get_material_by_time_label(self, time: int):
        return self.stock_dao.get_material_by_time_label(time)

    def change_material(self, material_id: int, location: str, diff_quantity: int, time_label: int):
        try:
            if material := self.stock_dao.get_material_by_id(material_id) is not None:
                if material.unique is True:
                    material = self.stock_dao.change_material(
                        material_id=material_id,
                        location=location,
                        quantity=0,
                        time_label=time_label
                    )
                else:
                    material = self.stock_dao.change_material(
                        material_id=material_id,
                        location="",
                        quantity=diff_quantity,
                        time_label=time_label
                    )
            return material
        except Exception as e:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail='Less zero')
