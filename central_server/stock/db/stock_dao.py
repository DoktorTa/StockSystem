from sqlalchemy.exc import IntegrityError, SQLAlchemyError
from sqlalchemy.orm import Session

from stock.db.__main__ import load_paint
from stock.db.paint import Paint


class StockDao:
    paints: Paint

    def __init__(self):
        self.paints: Paint = Paint()
        load_paint()

    @staticmethod
    def get_all_paints_names(session: Session):
        """
        NameCreator, NameLine
        """
        try:
            numes = session.query(Paint).add_column(Paint.name_creator, Paint.name_line)
            session.close()
            return numes
        except IntegrityError as e:
            raise e.orig
        except SQLAlchemyError as e:
            raise e
