class DataParser:
    prefix_id: int
    name_creator: str
    name_line: str
    volume: int
    url: str = ""
    path: str = ""
    possible_to_buy: int = 0

    class PrefixId:
        MONTANA_BLACK = "11"
        MONTANA_GOLD = "12"
        ARTON = "21"
        MOLOTOW_COVERSALL = "31"
        MOLOTOW_PREMIUM = "32"
        MONTANA_MTN94 = "41"
        LOOP = "51"


class MontanaBlack400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_BLACK
    name_creator = "MONTANA CANS"
    name_line = "BLACK - 400ml"
    volume = 400
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-400ml"


class MontanaGold400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_GOLD
    name_creator = "MONTANA CANS"
    name_line = "GOLD - 400ml"
    volume = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-colors"


class MolotowCoversall(DataParser):
    prefix_id = DataParser.PrefixId.MOLOTOW_COVERSALL
    name_creator = "MOLOTOW"
    name_line = "COVERSALL - 400ml"
    volume = 400
    url = "https://shop.molotow.com/en/coversall-color-spray-can.html"


class MolotowPremium(DataParser):
    prefix_id = DataParser.PrefixId.MOLOTOW_PREMIUM
    name_creator = "MOLOTOW"
    name_line = "PREMIUM - 400ml"
    volume = 400
    url = "https://shop.molotow.com/en/graffiti-spray-can-molotow-premium.html"


class MontanaColorsPremium(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_MTN94
    name_creator = "MONTANA COLORS"
    name_line = "MTN94 - 400ml"
    volume = 400
    possible_to_buy = 1
    url = "https://www.montanacolors.com/en/productos/mtn-94-aerosol-spray-paint/#colores"


class ArtonColor400Data(DataParser):
    prefix_id = DataParser.PrefixId.ARTON

    name_creator = "ARTON PAINT"
    name_line = "ARTON"
    volume = 400
    path = "Cans/Arton.csv"


class LoopColor400Data(DataParser):
    prefix_id = DataParser.PrefixId.LOOP

    name_creator = "LOOP COLORS"
    name_line = "LOOP"
    volume = 400
    path = "Cans/Loop.csv"

