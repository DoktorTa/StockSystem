from sqlalchemy import Column, Integer, String, Boolean
from sqlalchemy.dialects.postgresql import ARRAY


from db.database import DeclarativeBase


class Paint(DeclarativeBase):
    __tablename__ = 'paint'

    paint_id = Column(Integer, primary_key=True)
    data_time = Column('data_time', String)
    paint_type = Column('type', String)

    name_creator = Column('name_creator', String)
    name_line = Column('name_line', String)
    name_color = Column('name_color', String)
    code_paint = Column('code_paint', String)

    description_color = Column('description_color', String)
    color = Column('color', Integer)

    quantity_in_storage = Column('quantity_in_storage', Integer)

    possible_to_buy = Column('possible_to_buy', Boolean)

    similar_colors = Column('similar_colors', ARRAY(Integer, dimensions=2))

    def __repr__(self):
        return f'{self.paint_id}, {self.data_time}, {self.paint_type}, ' \
               f'{self.name_creator}, {self.name_line}, {self.name_color}, {self.code_paint}' \
               f'{self.description_color}, {self.color}' \
               f'{self.quantity_in_storage},' \
               f'{self.possible_to_buy},' \
               f'{self.similar_colors}'
