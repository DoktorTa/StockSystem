from abc import ABC, abstractmethod
from typing import List

import requests
from bs4 import BeautifulSoup

from cans_model import CansModel
from parser_data import DataParser


class _WebColorParser(ABC):
    data_parser: DataParser

    def get_list_html_page(self) -> List[str]:
        r = requests.get(self.data_parser.url)
        if r.status_code == 200:
            return self.get_list_html_cans(r.text)
        else:
            return []

    def get_list_cans_model(self) -> List[CansModel]:
        all_cans = []

        list_html_cans = self.get_list_html_page()
        for html_cans in list_html_cans:
            cans_model = self.html_cans2cans_model(html_cans)
            all_cans.append(cans_model)

        return all_cans

    @abstractmethod
    def html_cans2cans_model(self, html) -> CansModel:
        pass

    @abstractmethod
    def get_list_html_cans(self, html: str) -> List[str]:
        pass

