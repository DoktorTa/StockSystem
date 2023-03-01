# Пользователи
На сервере:
	Добавить пользователя
	Удалить пользователя
В приложении:
	Аутентификация

## Class

	UserServer 
		id: Int
		password: String - пароль НИКОГДА НЕ ХРАНИТСЯ НА КЛИЕНТЕ
		dataTime: String - дата регистрации
		name: String
		group: UserGroup - группа пользоватея

	UserClient 
		id: Int
		dataTime: String - дата регистрации
		name: String 
		group: UserGroup - группа пользоватея

	TicketServer
		ticketId: Int
		userId: User.id
		dataTimeEnd: String

	TicketClient
		ticketId: Int

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
	
## API 

	/login

### Группы пользователей
* ADMIN - Создатель
* ORGANIZER - Организаторы фестиваля  
* STORAGE - Гаражные
* ARTIST - Художника
* VOLUNTEER - Волантеры
* PRESS - Пресслужба

Каждая верхняя группа расширяет права нижней.

### Авторитизация и аутентификация
Пользователи не могут сами зарегестрироватся в системе.
<!---
@startuml
actor       Admin      as Admin
actor       User       as User
participant Server     as Server
database    DB         as DB

autonumber
Admin  -> Server : Добавление пользователя 
Server -> DB     : Добавление записи
Admin  -> User   : id, password
User   -> Server : id, password
Server -> DB     : id
DB     -> Server : obj UserServer
Server -> Server : auth

alt Аутентификация
    Server -> User   : obj UserClient без "password" и obj TicketClient
    Server -> DB     : obj TicketServer
else password или id не верны
    Server -> User   : AuthError
end
@enduml
-->

![Описание картинки](./images/aaa.png)

### Использование
<!---
@startuml
actor       User       as User
participant Server     as Server
database    DB         as DB

autonumber
User   -> Server : Запрос api, TicketClient 
Server -> DB     : TicketClient.ticketId

alt Проверка тикета
    DB     -> Server : TicketServer
    Server -> Server : Проверка тикета
else Ощибка в проверке тикета
    Server -> User   : TicketError
end

Server -> Server : обработка api
Server -> User   : ответ api
@enduml
-->

![Описание картинки](./images/work.png)