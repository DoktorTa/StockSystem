from pydantic import BaseModel


class GetUserResponse(BaseModel):
    user_name: str
    user_role: int


class LoginBase(BaseModel):
    login: str
    password: str


class Token(BaseModel):
    access_token: str
    refresh_token: str


class RefreshBase(BaseModel):
    refresh_token: str


class User(BaseModel):
    user_id: int
    user_name: str
    user_login: str
    user_password: str
    user_group: int
