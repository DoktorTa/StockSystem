import logging


class UserRepository:
    users = [
        {   # password
            'user_id': 1,
            'username': 'Tass',
            'login': 'admin',
            'password': br'$2b$12$uSQBzTEf/VmBucYKlIK8P.EoIybv3aLEWsvlJMaHFOLeUQHKzPVDW',
            'group': 0
        },
        {   # password1
            'user_id': 2,
            'username': 'Sveta',
            'login': 'stock',
            'password': b'$2b$12$4MynTSbvVNhGWJjXAtN7UOMxPifRe4sS2Bo.l5OLOrLw/qgtR0Oim',
            'group': 1
        }
    ]

    def get_user_by_login(self, login: str) -> dict | None:
        answer = None

        for user in self.users:
            if user['login'] == login:
                answer = user
                break
        logging.getLogger().info(answer)
        return answer
