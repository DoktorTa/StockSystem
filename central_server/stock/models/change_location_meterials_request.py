from pydantic import BaseModel


class ChangeLocationMaterialsRequest(BaseModel):
    material_id: int
    location: str
    time_label: int
