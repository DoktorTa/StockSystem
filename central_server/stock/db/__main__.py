import csv
import logging
import os
import sys

from sqlalchemy.dialects.postgresql import array, ARRAY, INTEGER
from sqlalchemy import Column, Integer, cast

from db.database import session_factory
from stock.db.paint import Paint


def load_paint():

    l = logging.getLogger()
    l.error("0")

    path = os.getenv('PATH_ALL_CSV')
    session = session_factory()

    reader = csv.reader(path)
    l.error("1")

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:
            similar_colors_new = []
            similar_colors = str(row[11]).replace('[', '').replace(']', '').split(',')
            # l.error(similar_colors)
            for i in similar_colors:
                if i != '':
                    s = array([int(x) for x in i.split(';')], type_=Integer)
                    similar_colors_new.append(s)

            if len(similar_colors_new) == 0:
                cast(array([]), ARRAY(Integer))
            else:
                similar_colors_new = array(similar_colors_new, type_=Integer)

            paint = Paint(
                paint_id=int(row[0]),
                data_time=row[2],
                paint_type=row[1],

                name_creator=row[3],
                name_line=row[4],
                name_color=row[6],
                code_paint=row[5],

                description_color=row[7],
                color=int(row[8]),

                quantity_in_storage=int(row[9]),

                possible_to_buy=bool(int(row[12])),

                similar_colors=similar_colors_new
            )

            session.add(paint)
            session.commit()

    session.close()
