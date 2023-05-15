from pydantic import BaseModel


class Material(BaseModel):
    id: int
    type: str
    description: str
    about: str
    unique: bool
    location: int

