from typing import List

from bs4 import BeautifulSoup

from cans_model import CansModel, TransferPaintModel
from parser_web import _WebColorParser


class MontanaColorsParserHTML(_WebColorParser):

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_html_cans(self, html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("div", class_="m-item_color")

        return list_html_cans

    def html_cans2cans_model(self, html) -> TransferPaintModel:
        color_code_color_name: str = html.find('div', class_="m-text").text.strip()

        delimetr = color_code_color_name.find(' ')
        color_code = color_code_color_name[:delimetr]

        name_color = color_code_color_name[delimetr:].strip()

        try:
            color = int(html.attrs['style'][18:24], 16)
            type_color = self.data_parser.type_colors
        except Exception as e:
            color = None
            type_color = None

        return TransferPaintModel(
            paint_name=name_color,
            color_code=color_code,
            color=color,
            paint_type=type_color
        )
