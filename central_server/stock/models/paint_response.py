from pydantic import BaseModel


class PaintResponse(BaseModel):
    time: int
    elements: list
