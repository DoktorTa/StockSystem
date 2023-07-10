from sqlalchemy import Column, Integer, String, Boolean, CheckConstraint

from src.main.db.database import Database


class Material(Database.DeclarativeBase):
    __tablename__ = 'materials'
    __table_args__ = {'extend_existing': True}

    material_id = Column('id', Integer, primary_key=True)
    material_type = Column('type', String)
    description = Column('description', String)
    time_label = Column('time_label', Integer)

    unique = Column('unique', Boolean)

    location = Column('location', String)
    quantity_in_storage = Column('quantity_in_storage', Integer, CheckConstraint("quantity_in_storage >= 0"))

    def __repr__(self):
        return f'{self.material_id}, {self.material_type}, {self.time_label}, ' \
               f'{self.description}, {self.unique}, {self.location}, {self.quantity_in_storage}'
