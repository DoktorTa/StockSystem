from pydantic import BaseModel


class LoginBase(BaseModel):
    login: str
    password: str
