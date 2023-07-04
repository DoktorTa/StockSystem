import csv
from typing import List

from cans_model import CansModel
from parser_data import DataParser


class ParserCSV:
    inc = 0

    data_parser: DataParser

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_cans_model(self) -> List[CansModel]:
        all_cans = []

        with open(self.data_parser.path, 'r') as file:
            reader = csv.DictReader(file)
            for row in reader:
                all_cans.append(self.csv_cans2cans_model(row))

        return all_cans

    def csv_cans2cans_model(self, cvs: dict) -> CansModel:
        name_color = cvs.get("name_color")
        code_color = f"{cvs.get('code_color')}-{self.data_parser.volume}"
        color = int(cvs.get("color")[1:], 16)

        paint_id = f'{self.data_parser.prefix_id}{self.inc:04}'
        self.inc += 1

        return CansModel(
            paint_id=paint_id,
            name_creator=self.data_parser.name_creator,
            name_line=self.data_parser.name_line,
            color_code=code_color,
            name_color=name_color,
            color=color,
            similar_colors=[],
            possible_to_buy=1
        )
