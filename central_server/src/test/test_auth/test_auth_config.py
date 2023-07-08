import os
import sys
from unittest.mock import MagicMock

import pytest
from fastapi import HTTPException

from schemas import User

# sys.modules['repository.user_repository'] = MagicMock()
os.environ.update({"JWT_TOKEN_SECRET": "secret"})
os.environ.update({"SERVER_NAME": "server"})
sys.modules['db.entitys.users'] = MagicMock()
sys.modules['db.users_dao'] = MagicMock()

from use_case.auth_config import AuthConfig
from use_case.jwt_token import JwtToken


class TestAuthConfig:
    password_no_encode = "password"
    user = User(
        user_id=1,
        user_name="test_name",
        user_password=JwtToken().password_hashing(password_no_encode),
        user_login="test_user",
        user_group=0
    )

    @pytest.fixture(autouse=True)
    def data_created(self, mocker):
        mocker.patch(
            "repository.user_repository.UserRepository.get_user_by_login",
            return_value=self.user
        )

    def test_authenticate_user_password_correct(self):
        auth_conf = AuthConfig()
        access_token, refresh_token = auth_conf.authenticate_user(self.user.user_login, self.password_no_encode)

        assert access_token is not None
        assert refresh_token is not None

    def test_authenticate_user_password_error(self):
        auth_conf = AuthConfig()

        with pytest.raises(HTTPException):
            auth_conf.authenticate_user(self.user.user_login, self.password_no_encode[1])

    def test_refresh_token_correct(self):
        auth_conf = AuthConfig()
        refresh_token = JwtToken().encode_refresh_token(self.user.user_login)
        access_token, refresh_token = auth_conf.refreshed_token(refresh_token)

        assert access_token is not None
        assert refresh_token is not None

    def test_authenticate_error_tocken(self, mocker):
        auth_conf = AuthConfig()

        mocker.patch(
            "repository.user_repository.UserRepository.get_user_by_login",
            return_value=None
        )

        with pytest.raises(HTTPException):
            auth_conf.refreshed_token("")
