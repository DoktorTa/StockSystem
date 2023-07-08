from fastapi import HTTPException
from starlette import status

from db.stock_dao import StockDao


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

    def update_location_material(self, material_id: int, location: str, time: int):
        try:
            return self.stock_dao.update_location_material(material_id, location, time)
        except Exception as e:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST)
