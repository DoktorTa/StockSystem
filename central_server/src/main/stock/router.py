import logging

from fastapi import APIRouter, Depends, Request

from src.main.auth.role_chacker import RoleChecker
from src.main.auth.models.role_user import RoleUser
from src.main.stock.repository.repository_stock import RepositoryStock
from src.main.stock.schemas import *

repository_stock = RepositoryStock()
router = APIRouter()


@router.post("/get_paints")
async def get_paint(
        request: Request,
        time_request: TimeRequest,
        user=Depends(RoleChecker(allowed_roles=RoleUser.GUIDE))
):
    logging.info(f'{request.client.host}:{request.client.port} - {user.user_name} - {time_request}')

    return ElementsResponse(
        elements=repository_stock.get_paint_by_time(time_request.timeLabel)
    )


@router.post("/change_quantity_paint")
async def change_quantity_paint(
        request: Request,
        update_request: UpdatePaintRequest,
        user=Depends(RoleChecker(allowed_roles=RoleUser.STOCK))
):
    logging.info(f'{request.client.host}:{request.client.port} - {user.user_name} - {update_request}')

    return ElementsResponse(
        elements=repository_stock.change_quantity_paint(
            paint_id=update_request.paint_id,
            diff_quantity=update_request.diff_quantity,
            time=update_request.time_label
        )
    )


@router.post("/get_materials")
async def get_materials(
        request: Request,
        time_request: TimeRequest,
        user=Depends(RoleChecker(allowed_roles=RoleUser.GUIDE))
):
    logging.info(f'{request.client.host}:{request.client.port} - {user.user_name} - {time_request}')

    return ElementsResponse(
        elements=repository_stock.get_material_by_time_label(time_request.timeLabel)
    )


@router.post("/change_material")
async def change_material(
        request: Request,
        change_request: ChangeMaterialsRequest,
        user=Depends(RoleChecker(allowed_roles=RoleUser.STOCK))
):
    logging.info(f'{request.client.host}:{request.client.port} - {user.user_name} - {change_request}')

    return ElementsResponse(
        elements=repository_stock.change_material(
            material_id=change_request.material_id,
            location=change_request.location,
            diff_quantity=change_request.diff_quantity,
            time_label=change_request.time_label
        )
    )

