import logging

from fastapi import APIRouter, Depends, Request

from src.main.auth.role_chacker import RoleChecker
from src.main.auth.use_case.auth_config import AuthConfig
from src.main.auth.schemas import *
from src.main.auth.models.role_user import RoleUser

router = APIRouter()
auth = AuthConfig()


@router.post('/login')
async def post_login(login_data: LoginBase) -> Token:
    access_token, refresh_token = auth.authenticate_user(**login_data.dict())
    return Token(access_token=access_token, refresh_token=refresh_token)


@router.post('/refresh_token')
async def post_refresh_token(refresh_data: RefreshBase) -> Token:
    access_token, refresh_token = auth.refreshed_token(**refresh_data.dict())
    return Token(access_token=access_token, refresh_token=refresh_token)


@router.post('/get_user')
async def get_user_by_access_token(
        request: Request,
        user=Depends(RoleChecker(allowed_roles=RoleUser.GUIDE))
) -> GetUserResponse:
    user_name, user_role = user.username, user.group
    logging.info(f'{request.client.host}:{request.client.port} - {user_name}')
    return GetUserResponse(user_name=user_name, user_role=user_role)
