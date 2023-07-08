import os

import pytest
from fastapi import HTTPException

os.environ.update({"JWT_TOKEN_SECRET": "secret"})
os.environ.update({"SERVER_NAME": "server"})

from use_case.jwt_token import JwtToken


class TestJwtToken:

    def test_password_check_and_hashing(self):
        password = "password"
        jwt_token = JwtToken()

        assert jwt_token.password_check(password, jwt_token.password_hashing(password)) is True

    def test_access_token_correct(self):
        login = "test_login"
        jwt_token = JwtToken()

        access_token = jwt_token.encode_access_token(login)
        login_answer = jwt_token.decode_access_token(access_token)

        assert login == login_answer

    def test_access_token_error(self):
        login = "test_login"
        jwt_token = JwtToken()

        refresh_token = jwt_token.encode_refresh_token(login)

        with pytest.raises(HTTPException):
            jwt_token.decode_access_token(refresh_token)

    def test_refresh_token_correct(self):
        login = "test_login"
        jwt_token = JwtToken()

        refresh_token = jwt_token.encode_refresh_token(login)
        login_answer = jwt_token.decode_refresh_token(refresh_token)

        assert login == login_answer

    def test_refresh_token_error(self):
        login = "test_login"
        jwt_token = JwtToken()

        access_token = jwt_token.encode_access_token(login)

        with pytest.raises(HTTPException):
            jwt_token.decode_refresh_token(access_token)
