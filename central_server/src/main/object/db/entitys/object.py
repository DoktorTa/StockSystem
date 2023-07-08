
class Object:
    __tablename__ = 'materials'

    object_id = Column('id', Integer, primary_key=True)
    object_name = Column('object_name', String)

    cordObject: ??? - Координаты
    обьекта
    на
    карте
    stateObject: String - Состояние
    обьекта
    dataStart: String - Время
    старта
    обьекта
    historyDataStart: List < String > - Предыдушие
    даты
    старта
    обьекта
    artistOnObject: List < User.id > - Художники
    responsibleForObject: List < User.id > - Люди
    которые
    ответственны
    за
    обьект
    pepoleInObject: List < User.id > - Люди
    которые
    находятся
    на
    обьекте
    прямо
    сейчас
    ordersOnObject: List < Order.id > - Заказы
    которые
    были
    вызванны
    на
    данный
    обьект

    material_id = Column('id', Integer, primary_key=True)
    material_type = Column('type', String)
    time_label = Column('time_label', Integer)

    description = Column('description', String)
    unique = Column('unique', Boolean)
    location = Column('location', String)

    def __repr__(self):
        return f'{self.material_id}, {self.material_type}, {self.time_label}, ' \
               f'{self.description}, {self.unique}, {self.location}'