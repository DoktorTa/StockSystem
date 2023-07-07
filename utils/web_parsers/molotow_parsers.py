from typing import List

from bs4 import BeautifulSoup

from cans_model import CansModel, TransferPaintModel
from parser_data import DataParser
from parser_web import _WebColorParser


class MolotowParserHTML(_WebColorParser):

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_html_cans(self, html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("div", class_="grid__options-item")

        return list_html_cans

    def html_cans2cans_model(self, html) -> TransferPaintModel:
        color_code = html.find('span', class_="art-nr").text

        label = html.find('span', class_='art-name')
        name_color = label.text

        try:
            color = int(html.find('div', class_='attribute').attrs['style'][19:25], 16)
            type_color = DataParser.TypeColors.CANS_DEFAULT
        except Exception as e:
            color = None
            type_color = None

        return TransferPaintModel(
            paint_name=name_color,
            color_code=color_code,
            color=color,
            paint_type=type_color
        )
