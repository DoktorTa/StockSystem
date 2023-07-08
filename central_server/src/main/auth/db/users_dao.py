from sqlalchemy.exc import IntegrityError, SQLAlchemyError

from db.entitys.users import Users
from src.main.db.database import Database


class UsersDao:
    users: Users

    def __init__(self):

        self.users: Users = Users()

    @staticmethod
    def get_password_by_login(login: str) -> Users | None:
        try:
            session = Database().session_factory()

            user = session.query(Users)\
                .filter(Users.user_login == login)\
                .first()

            session.close()

            return user
        except IntegrityError as e:
            raise e.orig
        except SQLAlchemyError as e:
            raise e
