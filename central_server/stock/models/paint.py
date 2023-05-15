from typing import List

from pydantic import BaseModel


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
