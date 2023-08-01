import os
from dotenv import load_dotenv

dotenv_path = os.path.join(os.path.dirname(__file__), '../../local.env')
if os.path.exists(dotenv_path):
    load_dotenv(dotenv_path)

import uvicorn
from fastapi import FastAPI

from auth.router import router as router_auth
from stock.router import router as router_stock
from object.router import router as router_object
from log_config import LOGGING_CONFIG

app = FastAPI()


@app.get('/')
async def login():
    return {"MES": "SEM"}

app.include_router(router_auth)
app.include_router(router_stock)
app.include_router(router_object)


if __name__ == "__main__":
    uvicorn.run(
        "main:app",
        reload=True,
        host="0.0.0.0",
        port=8000,
        # log_config=LOGGING_CONFIG
    )
