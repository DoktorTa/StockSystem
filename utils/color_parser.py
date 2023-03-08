from paint_model import PaintModelPaintId
from parsers_html import ParserHTML, MontanaCansParserHTML
from similar_color import SimilarColor


class ColorParser:
    calculate_similar_color = False
    name_creater = ""
    name_line = ""
    volume = 0
    url = ""

    def get_parser(self):
        pass


class MontanaBlackColorParser(ColorParser):
    calculate_similar_color = True
    name_creater = "MONTANA"
    name_line = "BLACK"
    volume = 400
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-400ml"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_BLACK)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


class MontanaBlackExtendedColorParser(ColorParser):
    calculate_similar_color = True
    name_creater = "MONTANA"
    name_line = "BLACK EXTENDAD"
    volume = 600
    url = r"https://" \
          r"www.montana-cans.com/en/spray-cans/montana-spray-paint/black-50ml-600ml-graffiti-paint/montana-black-extended-600ml"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_BLACK_EXTENDED)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


class MontanaGoldColorParser(ColorParser):
    calculate_similar_color = False
    name_creater = "MONTANA"
    name_line = "GOLD"
    value = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-colors"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_GOLD)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


class MontanaGoldFluorescentColorParser(ColorParser):
    calculate_similar_color = False
    name_creater = "MONTANA"
    name_line = "GOLD FLUORESCENT"
    value = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/gold-400ml-artist-paint/montana-gold-400ml-fluorescent-colors"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_GOLD_FLUORESCENT)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


class MontanaWhiteColorParser(ColorParser):
    calculate_similar_color = False
    name_creater = "MONTANA"
    name_line = "WHITE"
    value = 400
    url = "https://" \
          "www.montana-cans.com/en/spray-cans/montana-spray-paint/white-400ml-graffiti-paint/montana-white-400ml"

    def get_parser(self):
        array_id = PaintModelPaintId.generate_array_paint_id(PaintModelPaintId.Prefix.MONTANA_WHITE)
        return MontanaCansParserHTML(array_id, self.name_creater, self.name_line, self.volume)


def main():
    color_parser = MontanaBlackColorParser()

    parser = color_parser.get_parser()
    html = ParserHTML.get_html_page(color_parser.url)
    list_html_cans = ParserHTML.get_list_html_cans(html.text)

    similar_color = SimilarColor()
    if color_parser.calculate_similar_color:
        similar_color.load_all_color()

    all_id = []
    with open('Cans/montanaBlack.txt', 'w', encoding="utf-8") as file:
        for html_cans in list_html_cans:
            paint_model = parser.html_cans2paint_model(html_cans)

            if color_parser.calculate_similar_color:
                paint_model.similar_colors = similar_color.get_similar_color(paint_model.paint_id, paint_model.color)

            file.write(paint_model.get_kotlin_code() + '\n')
            file.flush()
            print(paint_model.get_kotlin_code())

        # Закомментируйте 3 строки ниже если не нужна какая либо агрегация в конце файла
            all_id.append(f"p{paint_model.paint_id}")
        l = f'listOf({str(all_id).replace("[", "").replace("]", "")})'
        file.write(l.replace("'", ""))

    similar_color.write_in_all_color()


if __name__ == '__main__':
    main()
