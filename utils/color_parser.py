from typing import List


class PaintModel:
    def __init__(self,
                 paint_id: int,
                 paint_type: str,
                 name_creater: str,
                 name_line: str,
                 name_paint: str,
                 name_color: str,
                 description_color: str,
                 color: int,
                 quantity_in_storage: int,
                 similar_colors: List[int],
                 possible_to_buy: bool
                 ):
        self.paint_id: int = paint_id
        self.paint_type: str = paint_type
        self.name_creater: str = name_creater
        self.name_line: str = name_line
        self.name_paint: str = name_paint
        self.name_color: str = name_color
        self.description_color: str = description_color
        self.color: int = color
        self.quantity_in_storage: int = quantity_in_storage
        self.places_of_possible_availability: List[str] = []
        self.similar_colors: List[int] = similar_colors
        self.possible_to_buy: bool = possible_to_buy

class PaintModelKotlinCode(PaintModel):
    def __str__(self):
        return f"{}"

    """
            val p5 = PaintModel(5, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1045-400", "MELON YELLOW", " ", 0xF59E01, 0, listOf(), listOf(), false)
    """