import logging

from fastapi import FastAPI, Response, Depends, HTTPException
# from fastapi.security import OAuth2PasswordBearer, SecurityScopes


from auth.auth_config import AuthConfig
from auth.models.token import Token
from auth.models.user_base import LoginBase, Group, User

# from stock.repository.repository_stock import RepositoryStock

app = FastAPI()
auth = AuthConfig()


class RoleChecker:
    def __init__(self, allowed_roles: int):
        self.allowed_roles = allowed_roles

    def __call__(self, user: User = Depends(auth.get_current_user)):
        if user.group > self.allowed_roles:
            raise HTTPException(status_code=403, detail="Operation not permitted")
        else:
            return True

    # def __call__(self, user: str):
    #     lof = logging.getLogger()
    #     lof.error("!!!!!!")
    #     # if user.group > self.allowed_roles:
    #     #     raise HTTPException(status_code=403, detail="Operation not permitted")
    #     # else:
    #     return True

# oauth_scheme = OAuth2PasswordBearer(
#     tokenUrl="token",
#     scopes={'items': 'permissions to access items'}
# )

# repository_stock = RepositoryStock()


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


@app.post('/token')
def login(login_data: LoginBase) -> Token:
    token_str = auth.authenticate_user(**login_data.dict())
    token = Token(access_token=token_str['access_token'], refresh_token=token_str['access_token'])
    return token


# @app.get("/getPaintById")
# async def get_paint_by_id(response: Response):
#     paint_id: int = response.body["id"]
#     return {"paint": repository_stock.get_paint_by_id(paint_id)}
