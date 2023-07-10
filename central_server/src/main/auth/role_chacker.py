from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials
from fastapi import HTTPException, Request

from src.main.auth.use_case.auth_config import AuthConfig


class RoleChecker(HTTPBearer):
    auth: AuthConfig = AuthConfig()

    def __init__(self, allowed_roles: int, auto_error: bool = True):
        super(RoleChecker, self).__init__(auto_error=auto_error)
        self.allowed_roles = allowed_roles

    async def __call__(self, request: Request):
        credentials: HTTPAuthorizationCredentials = await super(RoleChecker, self).__call__(request)

        if credentials:

            if not credentials.scheme == "Bearer":
                raise HTTPException(status_code=403, detail="Invalid authentication scheme.")

            user = self.auth.get_current_user(credentials.credentials)
            if user.user_group <= self.allowed_roles:
                return user
            else:
                raise HTTPException(status_code=403, detail="Invalid authentication scheme.")

        else:
            raise HTTPException(status_code=403, detail="Invalid authorization code.")
