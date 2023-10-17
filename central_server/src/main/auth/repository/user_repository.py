from auth.db.entitys.users import Users
from auth.db.users_dao import UsersDao


class UserRepository:
    user_dao: UsersDao

    def __init__(self):
        self.user_dao = UsersDao()

    def get_user_by_login(self, login: str) -> Users | None:
        return self.user_dao.get_password_by_login(login)
