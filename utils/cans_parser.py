import argparse
import csv

from similar_color import SimilarColor
from parser_csv import ParserCSV
from parser_data import *
from web_parsers.molotow_parsers import MolotowParserHTML
from web_parsers.montana_cans_parsers import MontanaCansParserHTML
from web_parsers.montana_colors_parsers import MontanaColorsParserHTML

output_types: list = ["csv", "kotlin"]

internet_parsers = [
    MontanaCansParserHTML(MontanaBlack400Data()),
    MontanaCansParserHTML(MontanaGold400Data()),
    MolotowParserHTML(MolotowCoversall()),
    MolotowParserHTML(MolotowPremium()),
    MontanaColorsParserHTML(MontanaColorsPremium())
]
csv_parsers = [
    ParserCSV(ArtonColor400Data()),
    ParserCSV(LoopColor400Data())
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


def write_to_csv(all_cans):

    with open('Cans/all.csv', 'w', newline='', encoding="utf-8") as file:
        writer = csv.writer(file)

        for cans in all_cans:
            writer.writerow(cans.get_csv())


def write_to_kotlin(all_cans):
    all_id = []

    with open('Cans/all.txt', 'w', encoding="utf-8") as file:
        for cans in all_cans:
            file.write(f'data.add({cans.get_kotlin_code()})\n')
            file.flush()
            all_id.append(f"p{cans.paint_id}")
        l = f'listOf({str(all_id).replace("[", "").replace("]", "")})'
        file.write(l.replace("'", ""))


def main():
    # output_type = pars_arg()
    output_type = 'csv'
    all_cans = get_all_cans_by_parser()

    similar_color = SimilarColor(all_cans)
    similar_color.calculate_simular_cans()

    if output_type == 'csv':
        write_to_csv(all_cans)
    else:
        write_to_kotlin(all_cans)


if __name__ == '__main__':
    main()
