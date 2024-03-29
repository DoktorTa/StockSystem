# Information
Данный документ ведется с 23.06.2023 => версии до этого здесь не прописаны.\
Структура релиза:
```
# [X.Y.Z] Version_name 
X - изменяется если происходят глобальные изменения, или изменения в старом api.
Y - изменяется по мере добавления новых фич.
Z - изменяется по мере фикса багов.
Description: 
## Part of the system (Android, IOs, Web, Server, Utils)
### List: Bugs, Fiches, Refactor, Redesign, Testing
After release: Data release [DD.MM.YYYY HH.MM]
```
В каждом релизе с изменением X должны быть:
* Bugs - должны быть исправлены все с меткой CRITICAL, PANIC, MAJOR.
* Refactor - должны быть исправлены не менее 1 ARCHITECTURE, 1 CODE.
* Testing - в случае наличия не протестированных частей системы, должен быть протестирован не меньше чем 1 модуль в любой части системы.
* Redesign - в случае наличия новых дизайн документов, должно быть переработано не менее чем 2 экрана в любой части системы.
* Fiches - ФИЧИ ЗАПРЕЩЕНЫ К ДОБОВЛЕНИЮ В ЭТОЙ ВЕРСИИ

В каждом релизе с изменением Y должны быть:
* Bugs - должны быть исправлены все с меткой CRITICAL, PANIC и не менее 1 бага MINIMAL.
* Refactor - должны быть исправлены не менее 1 CODE
* Fiches - не менее и желательно не более 1 полноценной рабочей фичи во всех частях системы

Виды багов:
* PANIC - часть системы падает.
* CRITICAL - серьезная ошибка, бизнес процессы не верны. 
* MAJOR - серьезные затруднения при работе с системой.
* MINIMAL - остальное.

Виды проблем для рефакторинга:
* ARCHITECTURE - с архитекурной точки зрения это плохо.
* CODE - код является плохим.

# Fiches: 
1. Поиск по названиям краски, возможно по цветам
2. Интеграция с Яндекс такси
3. Инкрементальные счетчики для некоторых материалов

# Bugs: 
1. MINIMAL - не изменяется количество краски на экране после изменения количества на складе - требуется перезаходить в экран краски

# Refactor
1. ARCHITECTURE - Переустройство системы логирования на сервере.
2. CODE - загрузка предварительных данных в базу данных

# Versions:
### Bugs:

# [1.Y.Z] Middle Ultra Violet
Description: release
## Deploy
1. docker - контейнеры 
2. docker-compose 
3. Последовательность развертки сервера и приложения.

## Server:
### Refactor
1. auth.db.utils (19, 37)
2. auth.models.group (1, 17)

## Utils:
### Fiches:
1. Add parsers molotow
2. Add parsers mnt94

### Bugs:
