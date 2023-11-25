MC Discord Activity - мод для Minecraft 1.7.10, который в активностях дискорда устанавливает кастомный текст с картинкой, а также меняет иконку и заголовок окна майнкрафта. Полезно для серверов.

## Общая информация

Этот репозиторий - проект Gradle, который должен быть открыт через IntelliJ IDEA или импортирован в Eclipse IDE.

| Технология                                    | Версия    |
|-----------------------------------------------|-----------|
| Версия системы автоматической сборки Gradle   | 8.4       |
| Версия Java, используемая для запуска Gradle  | 17.0.9    |
| Версия Java, используемая для запуска проекта | 17.0.9    |
| Версия Java, используемая для сборки проекта  | 1.8.0_392 |
| Уровень используемого в коде синтаксиса Java  | 8         |

## Установка

Все репозитории я оформляю в одном ключе, так что смотрите [общую инструкцию](https://github.com/Hummel009/Legendary-Item#readme) для установки любого Gradle-проекта.

## Использование

Инструкция пригодится как программистам, так и игрокам, просто программисты могут некоторые пункты выполнить иначе.

* Установите мод и откройте его при помощи WinRar. Первым делом вы увидите там файл настройки `.properties`, который можно открыть блокнотом.
* Открыв его, вы увидите там идентификатор, два логотипа, два названия на английском и три закодированных названия на русском.
  * Про названия на русском и английском говорить нечего, потому что их смысл станет очевиден, когда вы запустите игру и посмотрите в свой профиль в дискорде.
  * Чтобы редактировать русские слова в таком же духе, используйте [конвертер туда и обратно, в символы и в Java Unicode](https://r12a.github.io/app-conversion/).
  * Логотипы и идентификатор - это [ваше приложение в дискорде](https://discord.com/developers/applications). Создать его может любой желающий и программировать там ничего не надо. Копируем его идентификатор, загружаем в него два фото и указываем их имена в файле настроек мода. Profit!
    * Кэширование может занять пять или десять минут, так что загружайте фото с первого раза и терпеливо ожидайте.
* Иконка майнкрафта меняется по пути assets/mcda/icons.
