from src.main.object.db.object_dao import ObjectDao


class RepositoryObject:
    object_dao: ObjectDao

    def __init__(self):
        self.object_dao = ObjectDao()

    def get_object_by_time(self, time: int):
        return self.object_dao.get_objects_by_time(time)
