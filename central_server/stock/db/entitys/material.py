from sqlalchemy import Column, Integer, String, Boolean

from db.database import DeclarativeBase


class Material(DeclarativeBase):
    __tablename__ = 'materials'

    material_id = Column('id', Integer, primary_key=True)
    material_type = Column('type', String)
    time_label = Column('time_label', Integer)

    description = Column('description', String)
    unique = Column('unique', Boolean)
    location = Column('location', String)

    def __repr__(self):
        return f'{self.material_id}, {self.material_type}, {self.time_label}, ' \
               f'{self.description}, {self.unique}, {self.location}'
