class DataParser:
    prefix_id: int
    name_creator: str
    name_line: str
    volume: int
    url: str = ""
    path: str = ""

    class PrefixId:
        MONTANA_BLACK = "11"
        MONTANA_GOLD = "12"
        ARTON = "21"


class MontanaBlack400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_BLACK
    name_creator = "MONTANA CANS"
    name_line = "BLACK - 400ml"
    volume = 400
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-400ml"


class MontanaGold400Data:
    prefix_id = DataParser.PrefixId.MONTANA_GOLD
    name_creator = "MONTANA CANS"
    name_line = "GOLD - 400ml"
    volume = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-colors"


class ArtonColor400Data(DataParser):
    prefix_id = DataParser.PrefixId.ARTON

    name_creator = "ARTON PAINT"
    name_line = "ARTON"
    volume = 400
    path = "Cans/Arton.csv"

