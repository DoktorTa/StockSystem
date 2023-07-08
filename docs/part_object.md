# Обьекты
Часть приложения которая отвечает за обьекты фестиваля и работы с ними

## Class
	
	ObjectFestival
		id: Int
		nameObject: String - Название обьекта
		cordObject: ??? - Координаты обьекта на карте
		stateObject: String - Состояние обьекта
		dataStart: String - Время старта обьекта
		historyDataStart: List<String> - Предыдушие даты старта обьекта
		artistOnObject: List<User.id> - Художники
		responsibleForObject: List<User.id> - Люди которые ответственны за обьект
		pepoleInObject: List<User.id> - Люди которые находятся на обьекте прямо сейчас
		ordersOnObject: List<Order.id> - Заказы которые были вызванны на данный обьект

	Типы состояний обьектов:
		Запущен, незапущен, закончен

## Database

## API
