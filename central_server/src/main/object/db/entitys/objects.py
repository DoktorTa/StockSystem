from sqlalchemy import Column, Integer, String, ARRAY

from src.main.db.database import Database


class Objects(Database.DeclarativeBase):
    __tablename__ = 'objects'

    object_id = Column('id', Integer, primary_key=True)
    object_name = Column('object_name', String)
    state_object = Column('object_state', String)

    time_label = Column('time_label', Integer)

    cord_object = Column('cord_object', String)

    data_start = Column('data_start', Integer)
    history_data_start = Column('history_data_start', ARRAY(Integer, dimensions=1))

    artist_on_object = Column('artist_on_object', ARRAY(Integer, dimensions=1))
    responsible_for_object = Column('responsible_for_object', ARRAY(Integer, dimensions=1))
    people_in_object = Column('people_in_object', ARRAY(Integer, dimensions=1))

    orders_on_object = Column('orders_on_object', ARRAY(Integer, dimensions=1))

    def __repr__(self):
        return f'{self.object_id}, {self.object_name}, {self.state_object}, {self.time_label}' \
               f'{self.cord_object}, {self.data_start}, {self.history_data_start}, ' \
               f'{self.artist_on_object}, {self.responsible_for_object}. {self.people_in_object}, ' \
               f'{self.orders_on_object}'
