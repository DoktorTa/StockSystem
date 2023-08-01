# Склад

Склад работает с двумя важными обьектами:
1. Уникальные обьекты: Лесницы, Генераторы
2. Краска

## Class

	Уникальный обьект (Material):
		id: Int
		type: String - тип обьекта
		data_time: Int - время последнего изменения этого обьекта
		description: String - описание обьекта
		unique: Boolean - уникальный элемент (генератора) или не уникальный (кисточки)
		location: Object.id - местонахождение обьекта
	Пример: 
		id = 024923
		data_time = 01.01.2017 00:00:00
		type = "Лесница"
		description = "6 метровая, трех секционная лекция, каждая секция длинной 2 два метра"
		location = "Склад"
	Виды уникальных обьектов:
		Лесница, Генератор, Швабра, Секаторы, Лапаты

	Обьект краски (Paint):
		id: Int
		data_time: Int - время последнего изменения этого обьекта
		type: String - тип краски
		nameCreater: String - название производителя
		nameLine: String - название линейки краски
		codePaint: String - название краски (индентификатор) (номер в линейке)
		nameColor: String - название цвета (типо голубой)
		descriptionColor: String - описание цвета
		color: Int - цвет (dec)
		quantityInStorage: Int - колличество краски на складе 
		similarColors: List<Paint.id> - похожие цвета из других линеек или производителей
		possibleToBuy: Boolean - можно ли попробовать достать краску
	Пример:
		id = 1342
		data_time = 1346432
		type = "Банка"
		nameCreater = "ArtonPaint"
		nameLine = "Arton 400ml"
		codePaint = "R234"
		nameColor = "Голубой"
		descriptionColor = "Небесно голубой цвет"
		color: Int = 49151
		quantityInStorage: Int = 12 
		similarColors: List<Paint.id> = [2312, 2133]
		possibleToBuy = True
	
	Типы краски: 
		1. Сans Default
		2. Cans Transparent
		3. Cans Fluorescent
		4. Cans Chrome
		5. Cans Metallic
		6. Cans Special
		7. Exterior Paint


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
Method                             | SQL                                                                                 |
---------------------------------- | ----------------------------------------------------------------------------------- |
getListPaintsByLineAndCreator      | SELECT * FROM PrintTable WHERE nameCreator = :nameCreator AND nameLine = :nameLine  |
getPaintById                       | SELECT * FROM PrintTable WHERE id = id                                              |
getAllPaintNames                   | SELECT NameCreator, NameLine FROM PaintTable                                        |
insert                             | INSERT INTO PrintTable VALUES *                                                     |
delete                             | DELETE FROM PrintTable WHERE id = id                                                |

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

	/get_paint_by_time
		Права использования: >= PRESS
		GET 
		200 -> [PaintTable]
		400 -> Error

	/updateQuantityInStorage
		Права использования: >= STORAGE
		POST <- Paint
		200 -> Paint
		400 -> Error


## Работа с краской
У каждой позиции есть метка data_time - когда пользователь отправляет метку времени свойей базы, на сервере пользователю отправляются только то что старше этой ветки.

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

### Version 
В какой версии должна бать введена краска:

Версия | Название производителя | Название линейки       | Обьем | Добавленно | Выявлен цвет | Похожие цвета | Доступно в городе | 
------ | ---------------------- | ---------------------- | ----- | ---------- | ------------ | ------------- | ----------------- |
1.0    | Montana Cans           | BLACK                  | 400   | -          | +            | +             | -                 |
2.0    | Montana Cans           | GOLD                   | 400   | -          | +            | +             | -                 |
3.0    | Montana Cans           | WHITE                  | 400   | -          | +            | +             | -                 |
2.0    | Montana Colors         | MTN94                  | 400   | -          | +            | +             | -                 |
3.0    | Montana Colors         | Hardcore               | 400   | -          | +            | +             | -                 |
3.0    | MOLOTOW                | FLAME ORANGE           | 400   | -          | -            | -             | -                 |
3.0    | MOLOTOW                | FLAME ORANGE           | 600   | -          | -            | -             | -                 |
3.0    | MOLOTOW                | FLAME BLUE             | 400   | -          | -            | -             | -                 |
3.0    | MOLOTOW                | ONE4ALL ACRYLIC        | 400   | -          | -            | -             | -                 |
2.0    | MOLOTOW                | PREMIUM                | 400   | -          | -            | -             | -                 |
3.0    | MOLOTOW                | COVERSALL™ WATER-BASED | 400   | -          | -            | -             | -                 |
3.0    | MOLOTOW                | COVERSALL™ COLOR       | 400   | -          | -            | -             | -                 |
2.0    | Loop                   | LOOP                   | 400   | -          | -            | -             | -                 |
1.0    | ARTON                  | PAINT                  | 400   | -          | +            | +             | -                 |
1.0    | ARTON                  | PAINT                  | 600   | -          | -            | -             | -                 |
3.0    | Trane                  | TRANE                  | 400   | -          | -            | -             | -                 |
3.0    | Easy                   | EASY                   | 400   | -          | -            | -             | -                 |
3.0    | OneTake                | ONETAKE                | 400   | -          | -            | -             | -                 |
3.0    | RUSH                   | ART                    | 400   | -          | -            | -             | -                 |           
3.0    | RUSH                   | CHEAP                  | 400   | -          | -            | -             | -                 |
