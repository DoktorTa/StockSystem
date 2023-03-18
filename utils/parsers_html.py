from typing import List

import requests
from bs4 import BeautifulSoup

from paint_model import CansModel


class DontGatePage(Exception): pass


class ParserHTML:

    def __init__(self, array_id, name_creater, name_line, volume):
        self.array_id = array_id
        self.name_line = name_line
        self.name_creater = name_creater
        self.volume = volume

    @staticmethod
    def get_html_page(url: str) -> requests.Response:
        r = requests.get(url)
        if r.status_code == 200:
            return r
        else:
            raise DontGatePage

    @staticmethod
    def get_list_html_cans(html: str) -> List[str]:
        parsed_html = BeautifulSoup(html)
        list_html_cans = parsed_html.find_all("li", class_="color-variant-item")

        return list_html_cans

    def html_cans2cans_model(self, html) -> CansModel:
        pass


class MontanaCansParserHTML(ParserHTML):
    inc = 0

    def html_cans2cans_model(self, html) -> CansModel:

        cc = html.find('span', class_="color-code").text.replace(" ", "-")
        color_code = f"{cc}-{self.volume}"

        label = html.find('label')
        name_color = html.text.split('\n')[4]

        paint_id = self.array_id[self.inc]
        self.inc += 1

        color = int(label['data-hex'][1:], 16)

        return CansModel(
            paint_id,
            self.name_creater,
            self.name_line,
            color_code,
            name_color,
            color,
            []
        )
