import datetime
import json

from typing import List


class TransferPaintModel:

    def __init__(self,
                 paint_type: str,
                 color_code: str,
                 paint_name: str,
                 color: int
                 ):
        self.paint_type: str = paint_type
        self.color_code: str = color_code
        self.paint_name: str = paint_name
        self.color: int = color

    @staticmethod
    def get_sample_translation_csv():
        return ['name_color', 'color_code', 'color', 'paint_type']

    def get_translation_csv(self):
        if self.color is not None:
            hex_color = hex(self.color)[2:]
            hex_color_str = f'#{hex_color.zfill(6)}'
        else:
            hex_color_str = ""
        return [self.paint_name, self.color_code, hex_color_str, self.paint_type]



class CansModel:
    def __init__(self,
                 paint_id: str,
                 paint_type: str,
                 name_creator: str,
                 name_line: str,
                 color_code: str,
                 name_color: str,
                 color: int,
                 similar_colors: List[int],
                 possible_to_buy=0,
                 ):
        self.paint_id: str = paint_id
        self.paint_type: str = paint_type
        self.data_time: int = int(datetime.datetime.utcnow().timestamp())  # datetime.datetime.utcfromtimestamp(1685184037)
        self.name_creator: str = name_creator
        self.name_line: str = name_line
        self.color_code: str = color_code
        self.name_color: str = name_color
        self.description_color: str = " "
        self.color: int = color
        self.quantity_in_storage: int = 0
        self.places_of_possible_availability: List[str] = []
        self.similar_colors: List[int] = similar_colors
        self.possible_to_buy: int = possible_to_buy

    def get_kotlin_code(self):
        """
        val p5 = PaintModel(5, TypePaint.CANS, "MONTANA", "BLACK", "BLC-1045-400", "MELON YELLOW", " ", 0xF59E01, 0, listOf(), listOf(), false)
        """
        sc = str(self.similar_colors).replace("[", "").replace("]", "").replace("'", '')
        return rf'PaintModel({self.paint_id}, TypePaint.{self.paint_type}, "{self.name_creator}",' \
               rf' "{self.name_line}", "{self.color_code}", "{self.name_color}", " ", {hex(self.color)}, 0, listOf(),' \
               rf' listOf({sc}),  false)'

    @staticmethod
    def get_sample_csv():
        return ['paint_id', 'paint_type', 'name_creator', 'name_line', 'color_code',
                'name_color', 'description_color', 'color', 'quantity_in_storage',
                'places_of_possible_availability', 'similar_colors', 'possible_to_buy']

    def get_csv(self):
        return [self.paint_id, self.paint_type, self.data_time, self.name_creator, self.name_line, self.color_code,
                self.name_color, self.description_color, self.color, self.quantity_in_storage,
                self.places_of_possible_availability, str(self.similar_colors).replace("'", ""), self.possible_to_buy]


class CansModelEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, CansModel):
            return obj.__dict__
        return json.JSONEncoder.default(self, obj)
