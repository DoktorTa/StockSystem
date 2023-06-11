from pydantic import BaseModel


class User(BaseModel):
    user_id: int
    username: str
    login: str
    password: str
    group: int
