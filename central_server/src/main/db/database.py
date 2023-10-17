import os

from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base


class Database:

    url = os.getenv('DATABASE_URL')

    engine = create_engine(url, isolation_level="SERIALIZABLE")
    _SessionFactory = sessionmaker(bind=engine)
    DeclarativeBase = declarative_base()

    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Database, cls).__new__(cls)
        return cls.instance

    def session_factory(self):
        self.DeclarativeBase.metadata.create_all(self.engine)
        return self._SessionFactory()
