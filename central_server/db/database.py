import logging
import os

from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base

url = os.getenv('DATABASE_URL')

engine = create_engine(url, isolation_level="SERIALIZABLE")
_SessionFactory = sessionmaker(bind=engine)
DeclarativeBase = declarative_base()


def session_factory():
    DeclarativeBase.metadata.create_all(engine)
    return _SessionFactory()
