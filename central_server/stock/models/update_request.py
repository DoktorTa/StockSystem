from pydantic import BaseModel


class UpdatePaintRequest(BaseModel):
    id_paint: int
    diff_quantity: int
