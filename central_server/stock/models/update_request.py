from pydantic import BaseModel


class UpdatePaintRequest(BaseModel):
    time: int
    paint_id: int
    diff_quantity: int
