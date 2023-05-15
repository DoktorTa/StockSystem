from fastapi import Response

from main import app

from stock.repository.repository_stock import RepositoryStock

repository_stock = RepositoryStock()


# @app.get("/getAllPaint")
# async def get_all_paint():
#     return repository_stock.get_all_paint()


@app.get("/getPaintById")
async def get_paint_by_id(response: Response):
    paint_id: int = response.body["id"]
    return {"paint": repository_stock.get_paint_by_id(paint_id)}