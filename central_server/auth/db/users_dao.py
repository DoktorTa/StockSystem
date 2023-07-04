from sqlalchemy.exc import IntegrityError, SQLAlchemyError
from sqlalchemy.orm import Session

from auth.db.entitys.users import Users
from auth.db.utils import load_users


class UsersDao:
    users: Users

    def __init__(self):

        self.users: Users = Users()
        load_users()

    @staticmethod
    def get_password_by_login(session: Session, login: str) -> Users | None:
        try:
            user = session.query(Users)\
                .filter(Users.user_login == login)\
                .first()
            session.close()
            return user
        except IntegrityError as e:
            raise e.orig
        except SQLAlchemyError as e:
            raise e
