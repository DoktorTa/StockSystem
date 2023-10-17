from pydantic import BaseModel


class ElementsResponse(BaseModel):
    elements: list


class TimeRequest(BaseModel):
    timeLabel: int
