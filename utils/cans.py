import argparse

from color_parser import ColorParser, MontanaGoldColorParser, MontanaBlackColorParser, ArtonColorParser
from paint_model import PaintModelPaintId
from parsers_html import ParserHTML, MontanaCansParserHTML
from similar_color import SimilarColor


output_types: list = ["csv", "kotlin"]
cans_creators = ["ALL", "MONTANA_CANS"]
creators = {
    "MONTANA_CANS": [MontanaGoldColorParser, MontanaBlackColorParser],
    "ARTON": [ArtonColorParser]
}


def pars_arg():
    parser = argparse.ArgumentParser(description="Color parser script")

    parser.add_argument("-i", dest="name_color_update", default="all", type=str,
                        help=f"{cans_creators} - цвета какого производителя необходимо обновить")
    parser.add_argument("-o", dest="output_type", default="csv", type=str,
                        help=f"{output_types} - тип файла вывода")

    args = parser.parse_args()

    print(args)
    # rc, message = ping_ip(args.ip, args.count)


def parse_by_url(color_parser: ColorParser, parser: ParserHTML):
    all_cans = []

    html = ParserHTML.get_html_page(color_parser.url)
    list_html_cans = ParserHTML.get_list_html_cans(html.text)
    for html_cans in list_html_cans:
        cans_model = parser.html_cans2cans_model(html_cans)
        all_cans.append(cans_model)

    return all_cans


def main():
    name_color_update = ""
    parsers = [MontanaGoldColorParser(), MontanaBlackColorParser()]

    if name_color_update != "ALL":
        pass

    all_cans = []
    similar_color = SimilarColor()

    for color_parser_class in parsers:
        color_parser = color_parser_class
        parser = color_parser.get_parser()

        if color_parser.url != "":
            all_cans += parse_by_url(color_parser, parser)
        else:
            pass

    similar_color = SimilarColor()
    similar_color.all_cans += all_cans
    all_id = []

    with open('Cans/all.txt', 'w', encoding="utf-8") as file:
        for cans in all_cans:
            similar_color.get_similar_color(cans)
            file.write(cans.get_kotlin_code() + "\n")
            file.flush()
            all_id.append(f"p{cans.paint_id}")
        l = f'listOf({str(all_id).replace("[", "").replace("]", "")})'
        file.write(l.replace("'", ""))


if __name__ == '__main__':
    main()
