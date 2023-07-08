import logging

from fastapi import APIRouter, Depends, Request

from src.main.auth.role_chacker import RoleChecker
from models.role_user import RoleUser
from repository.repository_stock import RepositoryStock
from src.main.stock.schemas import *

repository_stock = RepositoryStock()
router = APIRouter()

#
# @router.post("/get_paints")
# async def get(
#         request: Request,
#         time_request: TimeRequest,
#         user=Depends(RoleChecker(allowed_roles=RoleUser.GUIDE))
# ):
#     logging.info(f'{request.client.host}:{request.client.port} - {user.username} - {time_request}')
#
#     return ElementsResponse(
#         elements=repository_stock.get_paint_by_time(time_request.timeLabel)
#     )