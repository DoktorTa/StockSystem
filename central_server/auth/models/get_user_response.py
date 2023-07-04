from pydantic import BaseModel


class GetUserResponse(BaseModel):
    user_name: str
    user_role: int
