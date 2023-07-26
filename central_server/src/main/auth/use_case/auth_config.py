from fastapi import HTTPException
from starlette import status

from src.main.auth.use_case.jwt_token import JwtToken
from src.main.auth.repository.user_repository import UserRepository
from src.main.auth.schemas import User


class AuthConfig:
    user_repository: UserRepository
    jwt_token: JwtToken

    def __init__(
            self,
            user_repository: UserRepository = UserRepository(),
            jwt_token: JwtToken = JwtToken()
    ):
        self.user_repository = user_repository
        self.jwt_token = jwt_token

    def authenticate_user(self, login: str, password: str) -> (str, str):
        try:
            user = self.user_repository.get_user_by_login(login)

            if self.jwt_token.password_check(password, user.user_password.encode()):
                access_token = self.jwt_token.encode_access_token(user.user_login)
                refresh_token = self.jwt_token.encode_refresh_token(user.user_login)
                return access_token, refresh_token
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

        except Exception as e:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

    def refreshed_token(self, refresh_token: str) -> (str, str):
        try:
            login = self.jwt_token.decode_refresh_token(refresh_token)
            user = self.user_repository.get_user_by_login(login)

            if user is not None:
                access_token = self.jwt_token.encode_access_token(user.user_login)
                refresh_token = self.jwt_token.encode_refresh_token(user.user_login)
                return access_token, refresh_token
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

        except Exception as e:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

    def get_current_user(
            self,
            token: str
    ) -> User:
        login = self.jwt_token.decode_access_token(token)
        user = self.user_repository.get_user_by_login(login)
        if user is not None:
            return User(
                user_id=user.user_id,
                user_name=user.user_name,
                user_login=user.user_login,
                user_password=user.user_password,
                user_group=user.user_group
            )
