import csv
import os

from sqlalchemy.dialects.postgresql import array, ARRAY
from sqlalchemy import Integer, cast

from db.database import session_factory
from db.entitys.material import Material
from stock.db.entitys.paint import Paint


def load_paint():
    path = os.getenv('PATH_PRELOAD_PAINTS')
    session = session_factory()

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:
            similar_colors_new = []
            similar_colors = str(row[11]).replace('[', '').replace(']', '').split(',')
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
                data_time=int(row[2]),
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


def load_materials():
    path = os.getenv('PATH_PRELOAD_MATERIALS')
    session = session_factory()

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:
            material = Material(
                material_id=int(row[0]),
                material_type=row[1],
                time_label=int(row[2]),

                description=row[4],
                unique=bool(int(row[4])),
                location=row[5]
            )
            session.add(material)
            session.commit()

    session.close()
