# Склад

Склад работает с двумя важными обьектами:
1. Уникальные обьекты: Лесницы, Генераторы
2. Краска

## Class

	Уникальный обьект (Material):
		id: Int
		type: String - тип обьекта
		description: String - описание обьекта
		about: String - другие дополнение к обьекту
		unique: Boolean - уникальный элемент (генератора) или не уникальный (кисточки)
		location: Object.id - местонахождение обьекта
	Пример: 
		id = 024923
		type = "Лесница"
		description = "6 метровая, трех секционная лекция, каждая секция длинной 2 два метра"
		about = "Не забудте о том что лесница тяжёлая"
		location = "Склад"
	Виды уникальных обьектов:
		Лесница, Генератор, Швабра, Секаторы, Лапаты

	Обьект краски (Paint):
		id: Int
		type: String - тип краски
		nameCreater: String - название производителя
		nameLine: String - название линейки краски
		namePaint: String - название краски (индентификатор) (номер в линейке)
		nameColor: String - название цвета (типо голубой)
		descriptionColor: String - описание цвета
		color: Int - цвет (dec)
		quantityInStorage: Int - колличество краски на складе 
		placesOfPossibleAvailability: List<Object.id> - места где может находится краска кроме склада
		similarColors: List<Paint.id> - похожие цвета из других линеек или производителей
	Пример:
		id = 1342
		type = "Банка"
		nameCreater = "ArtonPaint"
		nameLine = "Arton 400ml"
		namePaint = "R234"
		nameColor = "Голубой"
		descriptionColor = "Небесно голубой цвет"
		color: Int = 49151
		quantityInStorage: Int = 12 
		placesOfPossibleAvailability: List<Object.id> = [001, 342]
		similarColors: List<Paint.id> = [2312, 2133]
	Типы краски: 
		Банка, НитроЭмаль, Фасадная, ПФ 


	Обьект фотографии краски (PaintPhoto):
		id: Paint.id
		photo: Map<String, ByteArray> - что за фото, фото


## Database
	
MaterialTable:
Method         | SQL                                                   |
-------------- | ----------------------------------------------------- |
getAll         | SELECT * FROM UniqueElementTable                      |
getById        | SELECT * FROM UniqueElementTable WHERE id = id        |
updateLocation | UPDATE UniqueElementTable SET location WHERE id = id  |
delete         | DELETE FROM UniqueElementTable WHERE id = id          | 
insert         | INSERT INTO UniqueElementTable VALUES *               | 
	
PaintTable:
Method                             | SQL                                                                    |
---------------------------------- | ---------------------------------------------------------------------- |
getAll                             | SELECT * FROM PrintTable                                               |
getById                            | SELECT * FROM PrintTable WHERE id = id                                 |
updateQuantityInStorage            | UPDATE PrintTable SET quantityInStorage WHERE id = id                  |
insert                             | INSERT INTO PrintTable VALUES *                                        |
delete                             | DELETE FROM PrintTable WHERE id = id                                   |
update                             | UPDATE PrintTable SET * WHERE id = id                                  |
updatePlacesOfPossibleAvailability | UPDATE PrintTable SET updatePlacesOfPossibleAvailability WHERE id = id |

PaintPhotoTable:
Method                             | SQL                                         |
---------------------------------- | ------------------------------------------- |
getById                            | SELECT * FROM PaintPhotoTable WHERE id = id |
insert                             | INSERT INTO PaintPhotoTable VALUES *        |
delete                             | DELETE FROM PaintPhotoTable WHERE id = id   |


## API
	/getAllMaterials
		Права использования: >= PRESS
		GET 
		200 -> [UniqueElement]
		400 -> Error

	/getByIdMaterial
		Права использования: >= PRESS
		GET 
		200 -> UniqueElement
		400 -> Error

	/getAllPaint
		Права использования: >= PRESS
		GET 
		200 -> [PaintTable]
		400 -> Error

	/getByIdPaint
		Права использования: >= PRESS
		GET 
		200 -> Paint
		400 -> Error

	/updateQuantityInStorage
		Права использования: >= STORAGE
		POST <- Paint
		200 -> Paint
		400 -> Error

	/getByIdPaintPhoto
		Права использования: >= PRESS
		GET 
		200 -> PaintPhoto
		400 -> Error


### Materials 
Поскольку у данный группы элементов со склада очень много различных групп (Генераторы, лесницы, кисти и т.д.) то у этой группы должна быть только таблица в которой они собраны.
Если элемент не уникальный, то ему не выдается никакая локация кроме локации склада.

### Краска
* Работа с краской
 	* Список всех видов и производителей краски (PaintGroup)
	* Список всех имеющищся цветов (PaintColor)
	* Список всех цветов в наличии 
	* Страница Цвета (ColorPage)
* Работа со складом
* Работа с художником

##### Страница раздела
Два основнымх меню
Первое - меню выбора уникальных элементов
Второе - меню выбора краски

##### Страница выбора линейки && производителя && элемент
Эти страницы должны быть унифицированы.

##### Страница цвета
Обьект цвета должен хранить в себе

Действие или название поля                          | [surely или optional] | [group users] | [data type]        |
--------------------------------------------------- | --------------------- | ------------- | ------------------ |
Название производителя                              | [surely]              | [VOLUNTEER]   | [String]           |
Название линейки краски                             | [surely]              | [VOLUNTEER]   | [String]           |
Название Краски                                     | [surely]              | [VOLUNTEER]   | [String]           |
Название цвета                                      | [optional]            | [VOLUNTEER]   | [String]           |
HEX HSL CYMK цвета                                  | [surely]              | [VOLUNTEER]   | [List<Color>]      | 
Обьем цвета в наличии                               | [surely]              | [VOLUNTEER]   | [Int]              |
Похожие цвета из других линеек или производителей   | [optional]            | [VOLUNTEER]   | [List<String>]     |
Цвет                                                | [surely]              | [VOLUNTEER]   | [List<BytesArray>] |
Фотографии цвета на поверхностях                    | [optional]            | [VOLUNTEER]   | [List<BytesArray>] |
На каких обьектах данная краска может быть          | [optional]            | [STORAGE]     | [List<Paint.id>]   |
--------------------------------------------------- | --------------------- |-------------- | ------------------ |
Добавить красску в заказ                            | [surely]              | [VOLUNTEER]   |                    |
Изменить колличество краски на складе               | [surely]              | [STORAGE]     |                    |
Перейти в свой заказ                                | [surely]              | [VOLUNTEER]   |                    |

Если вы пользователь группы VOLUNTEER то вы не должны видеть поля или действия для STORAGE.

Действия добавить краску в заказ и изменить колличество красски должно быть в отдельном диалоговом окне.


