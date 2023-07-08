from typing import List

from pydantic import BaseModel


class ElementsResponse(BaseModel):
    elements: list


class TimeRequest(BaseModel):
    timeLabel: int


class Material(BaseModel):
    id: int
    type: str
    description: str
    about: str
    unique: bool
    location: int


class ChangeLocationMaterialsRequest(BaseModel):
    material_id: int
    location: str
    time_label: int


class Paint(BaseModel):
    paint_id: int
    paint_type: str
    nameCreator: str
    nameLine: str
    codePaint: str
    nameColor: str
    descriptionColor: str
    color: int
    quantityInStorage: int
    placesOfPossibleAvailability: List[int]
    similarColors: List[List[int]]
    possibleToBuy: bool


class UpdatePaintRequest(BaseModel):
    time_label: int
    paint_id: int
    diff_quantity: int
