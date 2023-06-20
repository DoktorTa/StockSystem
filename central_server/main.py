from logger import logger

import uvicorn
from fastapi import Request
from fastapi import FastAPI, Depends

from auth.api import router as router_auth
from stock.api import router
from log_config import LOGGING_CONFIG


app = FastAPI()

app.include_router(router_auth)
app.include_router(router)


if __name__ == "__main__":
    # uvicorn.run(app, host="192.168.1.112", port=8000)
    uvicorn.run(app, host="0.0.0.0", port=8000, log_config=LOGGING_CONFIG)
    # uvicorn.run(app, host="0.0.0.0", port=8000)

