from pydantic import BaseModel


class ElementsResponse(BaseModel):
    elements: list
