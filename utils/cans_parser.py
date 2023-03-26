import argparse
import csv
import json

from cans_model import CansModel, CansModelEncoder
from similar_color import SimilarColor
from parser_csv import ParserCSV
from parser_web import MontanaCansParserHTML
from parser_data import *


output_types: list = ["csv", "kotlin"]

internet_parsers = [
    MontanaCansParserHTML(MontanaBlack400Data()),
    MontanaCansParserHTML(MontanaGold400Data())
]
csv_parsers = [
    ParserCSV(ArtonColor400Data())
]


def pars_arg():
    parser = argparse.ArgumentParser(description="Color parser script")
    parser.add_argument("-o", dest="output_type", default="csv", type=str,
                        help=f"{output_types} - тип файла вывода")
    args = parser.parse_args()

    return args.output_type


def get_all_cans_by_parser() -> list:
    all_cans = []

    for parser in internet_parsers:
        all_cans += parser.get_list_cans_model()

    for parser in csv_parsers:
        all_cans += parser.get_list_cans_model()

    return all_cans


def main():
    output_type = pars_arg()
    all_cans = get_all_cans_by_parser()

    similar_color = SimilarColor(all_cans)
    similar_color.calculate_simular_cans()

    all_id = []

    # with open('Cans/all.json', 'w', encoding="utf-8") as file:
    #     json.dump(all_cans, file, cls=CansModelEncoder)

    with open('Cans/all.txt', 'w', encoding="utf-8") as file:
        for cans in all_cans:
            file.write(f'data.add({cans.get_kotlin_code()})\n')
            file.flush()
            all_id.append(f"p{cans.paint_id}")
        l = f'listOf({str(all_id).replace("[", "").replace("]", "")})'
        file.write(l.replace("'", ""))


if __name__ == '__main__':
    main()
