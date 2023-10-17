class RoleUser:  # TODO: передалать
    ADMIN = 0
    ORGANIZER = 1
    STOCK = 2
    ARTIST = 3
    VOLUNTEER = 4
    PRESS = 5
    GUIDE = 6

    @staticmethod
    def get_group_by_string(s: str) -> int:
        if s == 'ADMIN':
            return RoleUser.ADMIN
        elif s == 'ORGANIZER':
            return RoleUser.ORGANIZER
        elif s == 'STOCK':
            return RoleUser.STOCK
        elif s == 'ARTIST':
            return RoleUser.ARTIST
        elif s == 'VOLUNTEER':
            return RoleUser.VOLUNTEER
        elif s == 'PRESS':
            return RoleUser.PRESS
        elif s == 'GUIDE':
            return RoleUser.GUIDE
        else:
            return RoleUser.GUIDE
