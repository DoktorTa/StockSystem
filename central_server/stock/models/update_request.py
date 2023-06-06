from pydantic import BaseModel


class UpdatePaintRequest(BaseModel):
    time: int
    id_paint: int
    diff_quantity: int
