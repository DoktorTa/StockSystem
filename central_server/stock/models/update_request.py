from pydantic import BaseModel


class UpdatePaintRequest(BaseModel):
    time_label: int
    paint_id: int
    diff_quantity: int
