import logging

from fastapi import APIRouter

from models.change_location_meterials_request import ChangeLocationMaterialsRequest
from stock.models.update_request import UpdatePaintRequest
from stock.repository.repository_stock import RepositoryStock
from stock.models.time_request import TimeRequest
from stock.models.elements_response import ElementsResponse

repository_stock = RepositoryStock()
router = APIRouter()


@router.post("/get_paints")
async def get_paint(time_request: TimeRequest):
    return ElementsResponse(
        elements=repository_stock.get_paint_by_time(time_request.time)
    )


@router.post("/change_quantity_paint")
async def change_quantity_paint(update_request: UpdatePaintRequest):
    return ElementsResponse(
        elements=repository_stock.change_quantity_paint(
            paint_id=update_request.paint_id,
            diff_quantity=update_request.diff_quantity,
            time=update_request.time
        )
    )


@router.post("/get_materials")
async def get_materials(time_request: TimeRequest):
    return ElementsResponse(
        elements=repository_stock.get_material_by_time_label(time_request.time)
    )


@router.post("/change_location_material")
async def change_location_material(change_request: ChangeLocationMaterialsRequest):
    return ElementsResponse(
        elements=repository_stock.update_location_material(
            material_id=change_request.material_id,
            location=change_request.location,
            time=change_request.time_label
        )
    )
