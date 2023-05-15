import logging
import os
from datetime import datetime, timedelta

import jwt
import bcrypt
from fastapi import HTTPException
from starlette import status


class JwtToken:
    server_name = os.getenv('SERVER_NAME')
    secret = os.getenv('JWT_TOKEN_SECRET')
    time_refresh = timedelta(days=1)

    @staticmethod
    def password_check(password: str, hashed_password: bytes) -> bool:
        return bcrypt.checkpw(password.encode(), hashed_password)

    def password_hashing(self, password: str) -> bytes:
        return bcrypt.hashpw(password.encode(), bcrypt.gensalt())

    def encode_access_token(self, login: str) -> str:
        payload = {
            'scope': 'access_token',
            'sub': login,
            'exp': datetime.utcnow() + self.time_refresh,
            'iat': datetime.utcnow()
        }
        return jwt.encode(
            payload=payload,
            key=self.secret,
            algorithm='HS256'
        )

    def decode_access_token(self, token) -> (str, str):
        try:
            payload = jwt.decode(token, key=self.secret, algorithms=['HS256'])
            if payload['scope'] == 'access_token':
                return payload['sub']
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid token')
        except jwt.ExpiredSignatureError:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Token expired')
        except jwt.InvalidTokenError:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid token')

    def encode_refresh_token(self, login: str):
        payload = {
            'iss': self.server_name,
            'scope': 'refresh_token',
            'sub': login,
            'jti': 1,  # TODO: id долен генериться сам
            'exp': datetime.utcnow() + self.time_refresh,
            'iat': datetime.utcnow()
        }
        return jwt.encode(
            payload,
            self.secret,
            algorithm='HS256'
        )

    def decode_refresh_token(self, token) -> (str, str):
        try:
            payload = jwt.decode(token, key=self.secret, algorithms=['HS256'])
            if payload['scope'] == 'refresh_token':
                login = self.decode_access_token(token)
                return self.encode_access_token(login)
            else:
                raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid token')
        except jwt.ExpiredSignatureError:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Token expired')
        except jwt.InvalidTokenError:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail='Invalid token')
