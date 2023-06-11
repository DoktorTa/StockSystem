from fastapi import APIRouter, Depends, HTTPException

from auth.auth_config import AuthConfig
from auth.models.token import Token
from auth.models.user_base import User
from auth.models.login_base import LoginBase
from auth.models.refresh_base import RefreshBase

router = APIRouter()
auth = AuthConfig()


class RoleChecker:
    def __init__(self, allowed_roles: int):
        self.allowed_roles = allowed_roles

    def __call__(self, user: User = Depends(auth.get_current_user)):
        if user.group > self.allowed_roles:
            raise HTTPException(status_code=403, detail="Operation not permitted")
        else:
            return True


@router.post('/login')
def login(login_data: LoginBase) -> Token:
    token_str = auth.authenticate_user(**login_data.dict())
    return Token(access_token=token_str['access_token'], refresh_token=token_str['refresh_token'])


@router.post('/refresh_token')
def refresh_token(refresh_data: RefreshBase) -> Token:
    token_str = auth.refreshed_token(**refresh_data.dict())
    return Token(access_token=token_str['access_token'], refresh_token=token_str['refresh_token'])
