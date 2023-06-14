import csv
import os

from auth.db.entitys.users import Users

from db.database import session_factory


def load_users():
    path = os.getenv('PATH_PRELOAD_USERS')
    session = session_factory()

    with open(path, 'r') as file:
        reader = csv.reader(file, delimiter=',')

        for row in reader:
            user = Users(
                user_id=int(row[0]),
                user_name=row[1],
                user_group=int(row[2]),
                user_login=row[3],
                user_password=row[4]
            )
            session.add(user)
            session.commit()

    session.close()
