from typing import List

from bs4 import BeautifulSoup, ResultSet

from cans_model import CansModel, TransferPaintModel
from parser_web import _WebColorParser


class MontanaCansParserHTML(_WebColorParser):

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_html_cans(self, html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("li", class_="color-variant-item")

        return list_html_cans

    def html_cans2cans_model(self, html) -> TransferPaintModel:
        color_code = html.find('span', class_="color-code").text.replace(" ", "-")

        label = html.find('label')
        name_color = html.text.split('\n')[4]

        color = int(label['data-hex'][1:], 16)

        return TransferPaintModel(
            paint_name=name_color,
            color_code=color_code,
            color=color,
            paint_type=self.data_parser.type_colors
        )
