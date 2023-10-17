import os

from dotenv import load_dotenv

dotenv_path = os.path.join(os.path.dirname(__file__), '../../local.env')
if os.path.exists(dotenv_path):
    load_dotenv(dotenv_path)

import csv

import bcrypt
from sqlalchemy.orm import Session

from auth.db.entitys.users import Users
from object.db.entitys.objects import Objects
from stock.db.entitys.material import Material
from stock.db.entitys.paint import Paint
from db.database import Database
from auth.models.role_user import RoleUser

from sqlalchemy.dialects.postgresql import array, ARRAY
from sqlalchemy import Integer, cast


def get_all_preload_files() -> list:
    dotenv_path = os.path.join(os.path.dirname(__file__), r'../../preload_data')
    return os.listdir(dotenv_path)


def print_preload_data_files(files):
    i = 0
    for file in files:
        print(f'{i}: {file}')
        i += 1
    print(f'{i}: all')


def load_users():
    path = os.getenv('PATH_PRELOAD_USERS')
    path = os.path.join(os.path.dirname(__file__), path)
    session: Session = Database().session_factory()

    Users.__table__.drop(Database().engine)  # TODO: CODE переделать на инсерт?
    Users.__table__.create(Database().engine)

    cur_id = 0

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:

            password = str(bcrypt.hashpw(row[3].encode(), bcrypt.gensalt()))[2:][:-1]

            user = Users(
                user_id=cur_id,
                user_name=row[0],
                user_group=RoleUser.get_group_by_string(row[1]),
                user_login=row[2],
                user_password=password
            )
            cur_id += 1
            session.add(user)
            session.commit()

    session.close()


def load_objects():
    path = os.getenv('PATH_PRELOAD_OBJECTS')
    path = os.path.join(os.path.dirname(__file__), path)
    session: Session = Database().session_factory()

    Objects.__table__.drop(Database().engine)  # TODO: CODE переделать на инсерт?
    Objects.__table__.create(Database().engine)

    cur_id = 0

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:

            object = Objects(
                object_id=int(row[0]),
                object_name = row[1],
                state_object = row[2],

                time_label = int(row[3]),

                cord_object = "",

                data_start = int(row[4]),
                history_data_start = [],

                artist_on_object = [],
                responsible_for_object = [],
                people_in_object = [],

                orders_on_object = [],
            )
            cur_id += 1
            session.add(object)
            session.commit()

    session.close()


def load_materials():
    path = os.getenv('PATH_PRELOAD_MATERIALS')
    path = os.path.join(os.path.dirname(__file__), path)
    session = Database().session_factory()

    Material.__table__.drop(Database().engine)  # TODO: CODE переделать на инсерт?
    Material.__table__.create(Database().engine)

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:
            material = Material(
                material_id=int(row[0]),
                material_type=row[1],
                time_label=int(row[2]),

                description=row[3],
                unique=bool(int(row[4])),
                location=row[5],
                quantity_in_storage=int(row[6])
            )
            session.add(material)
            session.commit()

    session.close()


def load_paint():
    path = os.getenv('PATH_PRELOAD_PAINTS')
    path = os.path.join(os.path.dirname(__file__), path)
    session = Database().session_factory()

    Paint.__table__.drop(Database().engine)  # TODO: CODE переделать на инсерт?
    Paint.__table__.create(Database().engine)

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
                paint_type=row[1],
                data_time=int(row[2]),

                name_creator=row[3],
                name_line=row[4],
                code_paint=row[5],
                name_color=row[6],

                description_color=row[7],
                color=int(row[8]),

                quantity_in_storage=int(row[9]),

                possible_to_buy=bool(int(row[12])),

                similar_colors=similar_colors_new
            )

            session.add(paint)
            session.commit()

    session.close()


def main():
    files = get_all_preload_files()
    print_preload_data_files(files)
    file_preload = 4

    if file_preload == len(files):
        load_objects()
        load_paint()
        load_materials()
        load_users()


if __name__ == '__main__':
    main()
