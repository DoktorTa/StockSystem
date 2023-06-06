import logging
from datetime import datetime

from fastapi import HTTPException
from starlette import status

from stock.db.stock_dao import StockDao
from db.database import session_factory


class RepositoryStock:
    stock_dao: StockDao

    def __init__(self):
        self.stock_dao = StockDao()

    def get_paint_by_time(self, time: int):
        new_time = int(datetime.utcnow().timestamp())
        return new_time, self.stock_dao.get_paint_by_time(session_factory(), time)

    def update_paint_by_id(self, id_paint: int, diff_quantity: int):
        answer = self.stock_dao.update_paint(session_factory(), id_paint, diff_quantity)
        if answer is False:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail='Less zero')
        else:
            return self.stock_dao.update_paint(session_factory(), id_paint, diff_quantity)
