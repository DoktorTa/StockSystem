import csv
from typing import List

from cans_model import CansModel
from parser_data import DataParser


class ParserCSV:
    inc = 0

    data_parser: DataParser

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_cans_model(self) -> List[dict]:
        all_cans = []

        with open(self.data_parser.path, 'r') as file:
            reader = csv.DictReader(file)
            for row in reader:
                all_cans.append(row)

        return all_cans

    def csv_cans2cans_model(self, cvs: dict) -> (DataParser.TypeColors, CansModel):
        name_color = cvs.get("name_color")
        code_color = f"{cvs.get('code_color')}"
        color = hex(int(cvs.get("color")[1:], 16))

        hex_color = f'0x{"0" * (8 - len(color))}{color[2:]}'
        print(hex_color)


        type_color = DataParser.TypeColors().get_type_by_str(cvs.get("type_color"))

        paint_id = f'{self.data_parser.prefix_id}{self.inc:04}'
        self.inc += 1

        return type_color, \
            CansModel(
                paint_id=paint_id,
                paint_type=type_color,
                name_creator=self.data_parser.name_creator,
                name_line=self.data_parser.name_line,
                color_code=code_color,
                name_color=name_color,
                color=color,
                similar_colors=[],
                possible_to_buy=1,
                lab=str(rgb2lab([int(hex_color[2:4], 16), int(hex_color[4:6], 16), int(hex_color[6:8], 16)]))
            )


def rgb2lab(input_color):

    num = 0
    RGB = [0, 0, 0]

    for value in input_color:
        value = float(value) / 255

        if value > 0.04045:
            value = ((value + 0.055) / 1.055) ** 2.4
        else:
            value = value / 12.92

        RGB[num] = value * 100
        num = num + 1

    XYZ = [0, 0, 0, ]

    X = RGB[0] * 0.4124 + RGB[1] * 0.3576 + RGB[2] * 0.1805
    Y = RGB[0] * 0.2126 + RGB[1] * 0.7152 + RGB[2] * 0.0722
    Z = RGB[0] * 0.0193 + RGB[1] * 0.1192 + RGB[2] * 0.9505
    XYZ[0] = round(X, 4)
    XYZ[1] = round(Y, 4)
    XYZ[2] = round(Z, 4)

    XYZ[0] = float(XYZ[0]) / 95.047  # ref_X =  95.047   Observer= 2°, Illuminant= D65
    XYZ[1] = float(XYZ[1]) / 100.0  # ref_Y = 100.000
    XYZ[2] = float(XYZ[2]) / 108.883  # ref_Z = 108.883

    num = 0
    for value in XYZ:

        if value > 0.008856:
            value = value ** (0.3333333333333333)
        else:
            value = (7.787 * value) + (16 / 116)

        XYZ[num] = value
        num = num + 1

    Lab = [0, 0, 0]

    L = (116 * XYZ[1]) - 16
    a = 500 * (XYZ[0] - XYZ[1])
    b = 200 * (XYZ[1] - XYZ[2])

    Lab[0] = round(L, 4)
    Lab[1] = round(a, 4)
    Lab[2] = round(b, 4)

    return Lab