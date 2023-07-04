from typing import List

from bs4 import BeautifulSoup

from cans_model import CansModel
from parser_web import _WebColorParser


class MontanaCansParserHTML(_WebColorParser):
    inc = 0

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_html_cans(self, html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("li", class_="color-variant-item")

        return list_html_cans

    def html_cans2cans_model(self, html) -> CansModel:
        cc = html.find('span', class_="color-code").text.replace(" ", "-")
        color_code = f"{cc}-{self.data_parser.volume}"

        label = html.find('label')
        name_color = html.text.split('\n')[4]

        paint_id = f'{self.data_parser.prefix_id}{self.inc:04}'
        self.inc += 1

        color = int(label['data-hex'][1:], 16)

        return CansModel(
            paint_id,
            self.data_parser.name_creator,
            self.data_parser.name_line,
            color_code,
            name_color,
            color,
            [],
            possible_to_buy=self.data_parser.possible_to_buy
        )
