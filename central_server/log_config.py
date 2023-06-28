import os
from logging.config import dictConfig


# TODO: переделать на загружаемый изначально конфиг
LOGGING_CONFIG = {
    "version": 1,
    "disable_existing_loggers": False,

    "formatters": {
        "default": {
            "format": "%(asctime)s | %(name)s | %(process)d:%(lineno)d " "%(levelname)s %(message)s",
            "datefmt": "%Y-%m-%d %H:%M:%S",
        },
        "json": {
            "()": "pythonjsonlogger.jsonlogger.JsonFormatter",
            "format": """
                    asctime: %(asctime)s
                    created: %(created)f
                    filename: %(filename)s
                    funcName: %(funcName)s
                    levelname: %(levelname)s
                    levelno: %(levelno)s
                    lineno: %(lineno)d
                    message: %(message)s
                    module: %(module)s
                    msec: %(msecs)d
                    name: %(name)s
                    pathname: %(pathname)s
                    process: %(process)d
                    processName: %(processName)s
                    relativeCreated: %(relativeCreated)d
                    thread: %(thread)d
                    threadName: %(threadName)s
                    exc_info: %(exc_info)s
                """,
            "datefmt": "%Y-%m-%d %H:%M:%S",
        },

        "defaultConsole": {
            "()": "uvicorn.logging.DefaultFormatter",
            "fmt": "%(levelprefix)s %(message)s",
            "use_colors": None,
        },

    },

    "handlers": {
        "errorLogFile": {
            "formatter": "json",
            "level": "ERROR",
            "class": "logging.handlers.RotatingFileHandler",
            "filename": os.getenv('PATH_ERROR_LOG'),
            "maxBytes": 10000000,
            "backupCount": 5,
        },
        "infoLogFile": {
            "formatter": "default",
            "level": "INFO",
            "class": "logging.handlers.RotatingFileHandler",
            "filename": os.getenv('PATH_INFO_LOG'),
            "maxBytes": 10000000,
            "backupCount": 2,
        },

        "default": {
            "formatter": "defaultConsole",
            "class": "logging.StreamHandler",
            "stream": "ext://sys.stderr",
        },
    },

    "root": {
        "level": "INFO",
        "handlers": [
            "errorLogFile",
            "infoLogFile",
            "default",
        ]
    }
}
