import logging

from fastapi import HTTPException
from starlette import status


from auth.jwt_token import JwtToken
from auth.models.user_base import User
from auth.repository.user_repository import UserRepository


class AuthConfig:
    user_repository: UserRepository = UserRepository()
    jwt_token: JwtToken = JwtToken()

    def authenticate_user(self, login: str, password: str) -> dict:
        try:
            user = self.user_repository.get_user_by_login(login)

            if self.jwt_token.password_check(password, user.user_password.encode()):
                access_token = self.jwt_token.encode_access_token(user.user_login)
                refresh_token = self.jwt_token.encode_refresh_token(user.user_login)
                return {'access_token': access_token, 'refresh_token': refresh_token}
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')
        except Exception as e:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

    def refreshed_token(self, refresh_token: str) -> dict:
        try:
            login = self.jwt_token.decode_refresh_token(refresh_token)
            user = self.user_repository.get_user_by_login(login)
            if user is not None:
                access_token = self.jwt_token.encode_access_token(user.user_login)
                refresh_token = self.jwt_token.encode_refresh_token(user.user_login)
                return {'access_token': access_token, 'refresh_token': refresh_token}
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')
        except Exception as e:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')

    def get_current_user(
            self,
            token: str
    ) -> User:
        loger = logging.getLogger()
        login = self.jwt_token.decode_access_token(token)
        loger.error(login)
        # login = decoded['sub']
        user = self.user_repository.get_user_by_login(login)
        user = self.user_repository.get_user_by_login(login)
        if user is not None:
            loger.error(f'{user}')
            return User(**user)
