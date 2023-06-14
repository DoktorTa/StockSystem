from pydantic import BaseModel


class TimeRequest(BaseModel):
    timeLabel: int
