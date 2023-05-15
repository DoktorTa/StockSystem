import logging

from fastapi import HTTPException, Depends
from fastapi.security import OAuth2PasswordBearer
from starlette import status
import traceback


from auth.jwt_token import JwtToken
from auth.models.user_base import User
from auth.repository.user_repository import UserRepository


class AuthConfig:
    userRepository: UserRepository = UserRepository()
    jwt_token: JwtToken = JwtToken()

    # oauth_scheme = OAuth2PasswordBearer(
    #     tokenUrl="token",
    #     scopes={'items': 'permissions to access items'}
    # )

    def authenticate_user(self, login: str, password: str) -> dict:
        try:
            user = self.userRepository.get_user_by_login(login)

            if self.jwt_token.password_check(password, user['password']):
                access_token = self.jwt_token.encode_access_token(user['login'])
                refresh_token = self.jwt_token.encode_refresh_token(user['login'])
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
        user = self.userRepository.get_user_by_login(login)
        if user is not None:
            loger.error(f'{user}')
            return User(**user)
        else:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid credentials')
