import logging

from fastapi import APIRouter, Depends, Request

from auth.role_chacker import RoleChecker
from auth.models.role_user import RoleUser
from object.repository.repository_object import RepositoryObject
from object.schemas import *

repository_object = RepositoryObject()
router = APIRouter()


@router.post("/get_object")
async def get(
        request: Request,
        time_request: TimeRequest,
        user=Depends(RoleChecker(allowed_roles=RoleUser.GUIDE))
):
    logging.info(f'{request.client.host}:{request.client.port} - {user.user_name} - {time_request}')
    return ElementsResponse(
        elements=repository_object.get_object_by_time(time_request.timeLabel)
    )
