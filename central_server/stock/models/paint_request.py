from pydantic import BaseModel


class PaintRequest(BaseModel):
    time: int
