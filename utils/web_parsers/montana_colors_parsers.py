from typing import List

from bs4 import BeautifulSoup

from cans_model import CansModel
from parser_web import _WebColorParser


class MontanaColorsParserHTML(_WebColorParser):
    inc = 0

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def get_list_html_cans(self, html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("div", class_="m-item_color")

        return list_html_cans

    def html_cans2cans_model(self, html) -> CansModel:
        color_code_color_name: str = html.find('div', class_="m-text").text.strip()

        delimetr = color_code_color_name.find(' ')
        cc = color_code_color_name[:delimetr]
        color_code = f"{cc}-{self.data_parser.volume}"

        name_color = color_code_color_name[delimetr:].strip()

        paint_id = f'{self.data_parser.prefix_id}{self.inc:04}'
        self.inc += 1

        try:
            color = int(html.attrs['style'][18:24], 16)
        except Exception as e:
            color = 1000000000001

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
