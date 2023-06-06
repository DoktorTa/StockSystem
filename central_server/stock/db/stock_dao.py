import logging

from sqlalchemy import update
from sqlalchemy.exc import IntegrityError, SQLAlchemyError
from sqlalchemy.orm import Session

from stock.db.__main__ import load_paint
from stock.db.paint import Paint


class StockDao:
    paints: Paint

    def __init__(self):
        self.paints: Paint = Paint()
        # load_paint()

    @staticmethod
    def get_paint_by_time(session: Session, time: int):
        try:
            names = session.query(Paint).where(Paint.data_time < time).all()
            return names
        except IntegrityError as e:
            raise e.orig
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()

    @staticmethod
    def update_paint(session: Session, paint_id: int, quantity: int):
        try:
            stmt = (
                update(Paint)
                .where(Paint.paint_id == paint_id)
                .values(quantity_in_storage=Paint.quantity_in_storage + quantity)
            )
            session.execute(stmt)
            session.commit()
            return session.query(Paint).where(Paint.paint_id == paint_id).get(1)
        except IntegrityError as e:
            return False
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()
