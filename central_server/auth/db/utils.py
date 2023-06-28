import csv
import logging
import os

import bcrypt
from sqlalchemy import insert
from sqlalchemy.orm import Session

from auth.db.entitys.users import Users

from db.database import session_factory, engine
from auth.models.group import Group


def load_users():
    path = os.getenv('PATH_PRELOAD_USERS')
    session: Session = session_factory()

    Users.__table__.drop(engine)  # TODO: CODE переделать на инсерт?
    Users.__table__.create(engine)

    cur_id = 0

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:

            password = str(bcrypt.hashpw(row[3].encode(), bcrypt.gensalt()))[2:][:-1]

            user = Users(
                user_id=cur_id,
                user_name=row[0],
                user_group=Group.get_group_by_string(row[1]),
                user_login=row[2],
                user_password=password
            )
            cur_id += 1
            session.add(user)
            session.commit()

    session.close()
