# Пользователи
На сервере:
	Добавить пользователя
	Удалить пользователя
В приложении:
	Аутентификация

## Class

	UserServer 
		id: Int
		name: String - Имя пользователя
		login: String - логин
		password: String - пароль НИКОГДА НЕ ХРАНИТСЯ НА КЛИЕНТЕ
		group: UserGroup - группа пользоватея

	UserClient 
		id: Int
		name: String 
		group: UserGroup - группа пользоватея

	UserGroup: Enum
		ADMIN
		ORGANIZER  
		STORAGE
		ARTIST
		VOLUNTEER
		PRESS

## Data base
	
	Users - table
	Tickets - table

### Preload data
Для того чтобы система сразу после запуска была готова к работе должны сушествовать предварительные данные.
Они должны быть написаны в формате который может быть прочтен человеком и должны быть загруженны в базу данных специальным скриптом.

## Auth
Аутентификация через jwt токены

## API 

	/api/auth

		/login 
			role: [PRESS]
			-> 
				login: String
				password: String
			<- 
				400 -> Токен аутентификации
					access_token: String,
					refresh_token: String
				401 -> Unauth

		/refresh_token
			role: [ADMIN]
			->
				refresh_token: String
			<-
				400 -> Токен аутентификации
					access_token: String,
					refresh_token: String
				401 -> Unauth

### Группы пользователей
* ADMIN - Создатель
* ORGANIZER - Организаторы фестиваля  
* STORAGE - Гаражные
* ARTIST - Художника
* VOLUNTEER - Волантеры
* PRESS - Пресслужба

Каждая верхняя группа расширяет права нижней.
