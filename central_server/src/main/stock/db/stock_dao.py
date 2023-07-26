from datetime import datetime
from typing import Optional, Type

from sqlalchemy import update
from sqlalchemy.exc import SQLAlchemyError
from sqlalchemy.orm import Session

from src.main.db.database import Database
from src.main.stock.db.entitys.paint import Paint
from src.main.stock.db.entitys.material import Material


class StockDao:
    paints: Paint

    def __init__(self):
        self.paints: Paint = Paint()

    @staticmethod
    def get_paint_by_time_label(time: int) -> Optional[list[Type[Paint]] | None]:
        session: Session = Database().session_factory()

        try:
            names = session.query(Paint).where(Paint.data_time > time).all()
            return names
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()

    @staticmethod
    def change_quantity_paint(paint_id: int, quantity: int, time: int) -> Optional[list[Type[Paint]] | None]:
        session: Session = Database().session_factory()

        try:
            new_time = int(datetime.utcnow().timestamp())
            stmt = (
                update(Paint)
                .where(Paint.paint_id == paint_id)
                .values(quantity_in_storage=Paint.quantity_in_storage + quantity, data_time=new_time)
            )
            session.execute(stmt)
            session.commit()
            return StockDao.get_paint_by_time_label(time)
        except SQLAlchemyError as e:
            session.rollback()
            raise e
        finally:
            session.close()

    @staticmethod
    def get_material_by_time_label(time: int) -> Optional[list[Type[Material]] | None]:
        session: Session = Database().session_factory()

        try:
            names = session.query(Material).where(Material.time_label > time).all()
            return names
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()

    @staticmethod
    def get_material_by_id(material_id: int) -> Optional[Type[Material] | None]:
        session: Session = Database().session_factory()

        try:
            material = session.query(Material).where(Material.material_id == material_id).all()[0]
            return material
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()

    @staticmethod
    def change_material(material_id: int, location: str, quantity: int, time_label: int) -> Optional[list[Type[Material]] | None]:
        session: Session = Database().session_factory()

        try:
            new_time = int(datetime.utcnow().timestamp())
            stmt = (
                update(Material)
                .where(Material.material_id == material_id)
                .values(
                    quantity_in_storage=Material.quantity_in_storage + quantity,
                    location=location,
                    data_time=new_time)
            )
            session.execute(stmt)
            session.commit()
            return StockDao.get_paint_by_time_label(time_label)
        except SQLAlchemyError as e:
            session.rollback()
            raise e
        finally:
            session.close()
