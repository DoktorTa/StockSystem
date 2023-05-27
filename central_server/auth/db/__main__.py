from .users import Users

from db.database import session_factory


def create_people():
    session = session_factory()
    tass = Users(
        user_id=1,
        user_name='Tass',
        user_group=0,
        user_login='admin',
        user_password=r'$2b$12$uSQBzTEf/VmBucYKlIK8P.EoIybv3aLEWsvlJMaHFOLeUQHKzPVDW')  # password
    anna = Users(
        user_id=2,
        user_name='Anna',
        user_group=1,
        user_login='stock',
        user_password=r'$2b$12$4MynTSbvVNhGWJjXAtN7UOMxPifRe4sS2Bo.l5OLOrLw/qgtR0Oim')  # password1
    session.add(tass)
    session.add(anna)
    session.commit()
    session.close()


def get_people():
    session = session_factory()
    people_query = session.query(Users)
    session.close()
    return people_query.all()
