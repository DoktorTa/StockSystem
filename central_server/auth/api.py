import logging

from fastapi import APIRouter, Depends, HTTPException, Request
from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials

from auth.auth_config import AuthConfig
from auth.models.token import Token
from auth.models.login_base import LoginBase
from auth.models.refresh_base import RefreshBase
from models.get_user_response import GetUserResponse
from models.group import Group

router = APIRouter()
auth = AuthConfig()


class RoleChecker(HTTPBearer):
    def __init__(self, allowed_roles: int, auto_error: bool = True):
        super(RoleChecker, self).__init__(auto_error=auto_error)
        self.allowed_roles = allowed_roles

    async def __call__(self, request: Request):
        credentials: HTTPAuthorizationCredentials = await super(RoleChecker, self).__call__(request)

        if credentials:

            if not credentials.scheme == "Bearer":
                raise HTTPException(status_code=403, detail="Invalid authentication scheme.")

            user = auth.get_current_user(credentials.credentials)
            if user.group <= self.allowed_roles:
                return user
            else:
                raise HTTPException(status_code=403, detail="Invalid authentication scheme.")

        else:
            raise HTTPException(status_code=403, detail="Invalid authorization code.")


@router.post('/login')
async def login(login_data: LoginBase) -> Token:
    token_str = auth.authenticate_user(**login_data.dict())
    return Token(access_token=token_str['access_token'], refresh_token=token_str['refresh_token'])


@router.post('/refresh_token')
async def refresh_token(refresh_data: RefreshBase) -> Token:
    token_str = auth.refreshed_token(**refresh_data.dict())
    return Token(access_token=token_str['access_token'], refresh_token=token_str['refresh_token'])


@router.post('/get_user')
async def get_user_by_access_token(request: Request, user=Depends(RoleChecker(allowed_roles=Group.GUIDE))) -> GetUserResponse:
    user_name, user_role = user.username, user.group
    logging.info(f'{request.client.host}:{request.client.port} - {user_name}')
    return GetUserResponse(user_name=user_name, user_role=user_role)
