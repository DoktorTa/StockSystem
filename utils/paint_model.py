from typing import List
import csv


class PaintModelPaintId:
    class Prefix:
        MONTANA_BLACK = "11"
        MONTANA_GOLD = "12"
        ARTON = "21"

    @staticmethod
    def generate_array_paint_id(prefix: str) -> List[str]:
        return [f'{prefix}{i:04}' for i in range(0, 300)]


class CansModel:
    def __init__(self,
                 paint_id: str,
                 name_creater: str,
                 name_line: str,
                 color_code: str,
                 name_color: str,
                 color: int,
                 similar_colors: List[int]
                 ):
        self.paint_id: str = paint_id
        self.paint_type: str = "CANS"
        self.name_creater: str = name_creater
        self.name_line: str = name_line
        self.color_code: str = color_code
        self.name_color: str = name_color
        self.description_color: str = " "
        self.color: int = color
        self.quantity_in_storage: int = 0
        self.places_of_possible_availability: List[str] = []
        self.similar_colors: List[int] = similar_colors
        self.possible_to_buy: bool = False

    def get_kotlin_code(self):
        """
        val p5 = PaintModel(5, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1045-400", "MELON YELLOW", " ", 0xF59E01, 0, listOf(), listOf(), false)
        """
        sc = str(self.similar_colors).replace("[", "").replace("]", "").replace("'", '')
        return rf'val p{self.paint_id} = ' \
               rf'PaintModel({self.paint_id}, TypePaint.{self.paint_type}, "{self.name_creater}",' \
               rf' "{self.name_line}", "{self.color_code}", "{self.name_color}", " ", {hex(self.color)}, 0, listOf(),' \
               rf' listOf({sc}),  false)'

    def get_csv(self):
        return rf'{self.paint_id},{self.paint_type},{self.name_creater},{self.name_line},' \
               rf'{self.color_code},{self.name_color},"",{self.color},0,"",{self.similar_colors},false'
