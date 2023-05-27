from pydantic import BaseModel


class LoginBase(BaseModel):
    login: str
    password: str


class RefreshBase(BaseModel):
    refresh_token: str


class User(BaseModel):
    user_id: int
    username: str
    login: str
    password: str
    group: int


class Group:
    ADMIN = 0
    ORGANIZER = 1



