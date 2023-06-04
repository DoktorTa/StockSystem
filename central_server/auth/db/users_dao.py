from sqlalchemy.exc import IntegrityError, SQLAlchemyError
from sqlalchemy.orm import Session

from auth.db.users import Users


class UsersDao:
    users: Users

    def __init__(self):
        self.users: Users = Users()

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
