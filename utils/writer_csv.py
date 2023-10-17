import csv
from typing import List

from cans_model import CansModel, TransferPaintModel


class WriterCsv:
    inc = 0

    def __init__(self, data_parser):
        self.data_parser = data_parser

    def write_transfer_data(self, all_cans: List[TransferPaintModel]):

        with open(self.data_parser.transfer_path, 'w', newline='', encoding="utf-8") as file:
            writer = csv.writer(file)

            for cans in all_cans:
                writer.writerow(cans.get_translation_csv())
