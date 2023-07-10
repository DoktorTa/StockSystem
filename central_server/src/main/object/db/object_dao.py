from typing import Optional, Type

from sqlalchemy.exc import SQLAlchemyError
from sqlalchemy.orm import Session

from src.main.object.db.entitys.objects import Objects
from src.main.db.database import Database


class ObjectDao:
    objects: Objects

    def __init__(self):
        self.objects: Objects = Objects()

    @staticmethod
    def get_objects_by_time(time: int) -> Optional[list[Type[Objects]] | None]:
        session: Session = Database().session_factory()

        try:
            names = session.query(Objects).where(Objects.time_label > time).all()
            return names
        except SQLAlchemyError as e:
            raise e
        finally:
            session.close()
