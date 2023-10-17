class DataParser:
    prefix_id: int
    name_creator: str
    name_line: str
    volume: int
    url: str = ""
    path: str = ""
    transfer_path: str = ""
    possible_to_buy: int = 0
    type_colors: str

    class PrefixId:
        MONTANA_BLACK = "11"
        MONTANA_GOLD = "12"
        MONTANA_GOLD_TRANSPARENT = "13"
        ARTON = "21"
        MOLOTOW_COVERSALL = "31"
        MOLOTOW_PREMIUM = "32"
        MONTANA_MTN94 = "41"
        LOOP = "51"
        EXTERIOR_RAL_CLASSIC = "61"

    class TypeColors:
        CANS_DEFAULT = 'default'
        CANS_TRANSPARENT = 'transparent'
        CANS_FLUORESCENT = 'fluorescent'
        CANS_CHROME = 'chrome'
        CANS_METALLIC = 'metallic'
        CANS_SPECIAL = 'special'
        EXTERIOR_PAINT = 'exterior'

        def get_type_by_str(self, type_str: str):
            if self.CANS_DEFAULT == type_str:
                return self.CANS_DEFAULT
            elif self.CANS_TRANSPARENT == type_str:
                return self.CANS_TRANSPARENT
            elif self.CANS_FLUORESCENT == type_str:
                return self.CANS_FLUORESCENT
            elif self.CANS_CHROME == type_str:
                return self.CANS_CHROME
            elif self.CANS_METALLIC == type_str:
                return self.CANS_METALLIC
            elif self.CANS_SPECIAL == type_str:
                return self.CANS_SPECIAL
            elif self.EXTERIOR_PAINT == type_str:
                return self.EXTERIOR_PAINT


class MontanaBlack400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_BLACK
    type_colors = DataParser.TypeColors.CANS_DEFAULT
    name_creator = "MONTANA CANS"
    name_line = "BLACK - 400ml"
    volume = 400
    path = "./Cans/sources/MontanaBlack.csv"
    transfer_path = "./Cans/sources/MontanaBlack.csv"
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-400ml"


class MontanaGold400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_GOLD
    type_colors = DataParser.TypeColors.CANS_DEFAULT
    name_creator = "MONTANA CANS"
    name_line = "GOLD - 400ml"
    volume = 400
    path = "./Cans/sources/MontanaGold.csv"

    transfer_path = "./Cans/sources/MontanaGold.csv"
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-colors"


class MontanaGoldTransparent400Data(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_GOLD_TRANSPARENT
    type_colors = DataParser.TypeColors.CANS_TRANSPARENT
    name_creator = "MONTANA CANS"
    name_line = "TRANSPARENT - 400ml"
    volume = 400
    path = "./Cans/sources/MontanaGoldTransparent.csv"
    transfer_path = "./Cans/sources/MontanaGoldTransparent.csv"
    url = "https://www.montana-cans.com" \
          "/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-transparent-colors"


class MolotowCoversall(DataParser):
    prefix_id = DataParser.PrefixId.MOLOTOW_COVERSALL
    name_creator = "MOLOTOW"
    name_line = "COVERSALL - 400ml"
    volume = 400
    path = "./Cans/sources/MolotowCoversall.csv"

    transfer_path = "./Cans/sources/MolotowCoversall.csv"
    url = "https://shop.molotow.com/en/coversall-color-spray-can.html"


class MolotowPremium(DataParser):
    prefix_id = DataParser.PrefixId.MOLOTOW_PREMIUM
    name_creator = "MOLOTOW"
    name_line = "PREMIUM - 400ml"
    volume = 400
    path = "./Cans/sources/MolotowPremium.csv"

    transfer_path = "./Cans/sources/MolotowPremium.csv"
    url = "https://shop.molotow.com/en/graffiti-spray-can-molotow-premium.html"


class MontanaColorsMTN94(DataParser):
    prefix_id = DataParser.PrefixId.MONTANA_MTN94
    type_colors = DataParser.TypeColors.CANS_DEFAULT
    name_creator = "MONTANA COLORS"
    name_line = "MTN94 - 400ml"
    volume = 400
    possible_to_buy = 1
    path = "./Cans/sources/MolotowMTN94.csv"

    transfer_path = "./Cans/sources/MolotowMTN94.csv"
    url = "https://www.montanacolors.com/en/productos/mtn-94-aerosol-spray-paint/#colores"


class ArtonColor400Data(DataParser):
    prefix_id = DataParser.PrefixId.ARTON

    name_creator = "ARTON PAINT"
    name_line = "ARTON - 400ml"
    volume = 400
    path = "Cans/sources/Arton.csv"
    type_colors = DataParser.TypeColors.CANS_DEFAULT


class RalClassicData(DataParser):
    prefix_id = DataParser.PrefixId.EXTERIOR_RAL_CLASSIC
    type_colors = DataParser.TypeColors.EXTERIOR_PAINT
    name_creator = "Exterior Ral"
    name_line = "Classic"
    volume = 1000
    path = "./Cans/sources/RalClassic.csv"


class LoopColor400Data(DataParser):
    prefix_id = DataParser.PrefixId.LOOP
    type_colors = DataParser.TypeColors.CANS_DEFAULT
    name_creator = "LOOP COLORS"
    name_line = "LOOP - 400ml"
    volume = 400
    path = "Cans/sources/Loop.csv"

