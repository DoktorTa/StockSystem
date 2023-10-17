import argparse
import csv
import os

from similar_color import SimilarColor
from parser_csv import ParserCSV
from parser_data import *
from web_parsers.molotow_parsers import MolotowParserHTML
from web_parsers.montana_cans_parsers import MontanaCansParserHTML
from web_parsers.montana_colors_parsers import MontanaColorsParserHTML
from writer_csv import WriterCsv

output_types: list = ["csv", "kotlin"]

internet_parsers = [
    # MontanaCansParserHTML(MontanaBlack400Data()), +
    # MontanaCansParserHTML(MontanaGold400Data()), +
    # MontanaCansParserHTML(MontanaGoldTransparent400Data()), +
    # MolotowParserHTML(MolotowCoversall()), +
    # MolotowParserHTML(MolotowPremium()), +
    # MontanaColorsParserHTML(MontanaColorsMTN94()) +
]
csv_parsers = [
    ParserCSV(MontanaBlack400Data()),
    ParserCSV(MontanaGold400Data()),
    ParserCSV(MontanaGoldTransparent400Data()),
    ParserCSV(MolotowCoversall()),
    ParserCSV(MolotowPremium()),
    ParserCSV(MontanaColorsMTN94()),
    ParserCSV(ArtonColor400Data()),
    ParserCSV(LoopColor400Data()),
    ParserCSV(RalClassicData()),
]


def pars_arg():
    parser = argparse.ArgumentParser(description="Color parser script")
    parser.add_argument("-o", dest="output_type", default="csv", type=str,
                        help=f"{output_types} - тип файла вывода")
    args = parser.parse_args()

    return args.output_type


def create_translation_data():
    for parser in internet_parsers:
        all_cans = parser.get_list_cans_model()
        WriterCsv(parser.data_parser).write_transfer_data(all_cans)


def collect_all_cvs():
    all_category_cans = {}

    for parser in csv_parsers:
        all_cans = parser.get_list_cans_model()
        for can in all_cans:
            type_str, cans_model = parser.csv_cans2cans_model(can)
            cans = all_category_cans.get(type_str)
            if cans is None:
                cans = []
            cans.append(cans_model)

            all_category_cans.update({type_str: cans})

    return all_category_cans


def write_to_csv(all_cans):

    with open('Cans/all.csv', 'a', newline='', encoding="utf-8") as file:
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


def write_to_txt(all_cans):

    with open('Cans/all_txt.csv', 'a', newline='', encoding="utf-8") as file:
        writer = csv.writer(file)

        for cans in all_cans:
            writer.writerow(cans.get_txt())


def main():
    # output_type = pars_arg()
    output_type = 'txt'
    all_cans = collect_all_cvs()

    for key, value in all_cans.items():
        # similar_color = SimilarColor(value)
        # similar_color.calculate_simular_cans()

        if output_type == 'csv':
            write_to_csv(value)
        elif output_type == 'txt':
            write_to_txt(value)
        else:
            write_to_kotlin(value)


if __name__ == '__main__':
    main()
