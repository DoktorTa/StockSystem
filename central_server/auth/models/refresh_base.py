from pydantic import BaseModel


class RefreshBase(BaseModel):
    refresh_token: str
