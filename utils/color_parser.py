import argparse

from paint_model import PaintModelPaintId
from parsers_html import ParserHTML, MontanaCansParserHTML
from similar_color import SimilarColor


class ColorParser:
    name_creator = ""
    name_line = ""
    volume = 0
    url = ""

    def get_parser(self):
        pass


class ArtonColorParser(ColorParser):
    name_creater = "ARTON PAINT"
    name_line = "ARTON"
    volume = 400
    url = ""

    def get_parser(self):
       pass


class MontanaBlackColorParser(ColorParser):
    name_creater = "MONTANA"
    name_line = "BLACK"
    volume = 400
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-400ml"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_BLACK)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


class MontanaGoldColorParser(ColorParser):
    name_creater = "MONTANA"
    name_line = "GOLD"
    value = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-colors"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_GOLD)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)

