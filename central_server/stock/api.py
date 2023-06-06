import logging

from fastapi import APIRouter

from stock.models.update_request import UpdatePaintRequest
from stock.repository.repository_stock import RepositoryStock
from stock.models.paint_request import PaintRequest
from stock.models.paint_response import PaintResponse

repository_stock = RepositoryStock()
router = APIRouter()


@router.post("/get_paint")
async def get_paint(time_request: PaintRequest):
    time, paints = repository_stock.get_paint_by_time(time_request.time)
    return PaintResponse(
        time=time,
        elements=paints
    )


@router.post("/update_paint")
async def update_paint(update_request: UpdatePaintRequest):
    return {"answer": repository_stock.update_paint_by_id(
        update_request.id_paint,
        update_request.diff_quantity)
    }