class Group:  # TODO: передалать
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
            return Group.ADMIN
        elif s == 'ORGANIZER':
            return Group.ORGANIZER
        elif s == 'STOCK':
            return Group.STOCK
        elif s == 'ARTIST':
            return Group.ARTIST
        elif s == 'VOLUNTEER':
            return Group.VOLUNTEER
        elif s == 'PRESS':
            return Group.PRESS
        elif s == 'GUIDE':
            return Group.GUIDE
        else:
            return Group.GUIDE
