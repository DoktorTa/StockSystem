import uvicorn
from fastapi import FastAPI, Depends

from auth.models.group import Group
from auth.api import router as router_auth, RoleChecker

from stock.api import router

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}


@app.get("/helloADMIN")
async def say_hello(authorize: bool = Depends(RoleChecker(allowed_roles=Group.ADMIN))):
    if authorize:
        return {'message': 'TRUE'}
    else:
        return {'message': 'FALSE'}

app.include_router(router_auth)
app.include_router(router)


if __name__ == "__main__":
    # uvicorn.run(app, host="192.168.1.112", port=8000)
    uvicorn.run(app, host="0.0.0.0", port=8000)
