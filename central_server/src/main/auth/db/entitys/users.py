from sqlalchemy import Column, Integer, String

from db.database import Database


class Users(Database.DeclarativeBase):
    __tablename__ = 'users'

    user_id = Column(Integer, primary_key=True)
    user_name = Column('name', String)
    user_login = Column('login', String)
    user_password = Column('password', String)
    user_group = Column('group', String)

    def __repr__(self):
        return f'{self.user_id}, {self.user_name}, {self.user_group}, {self.user_login}, {self.user_password}'
