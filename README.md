[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=code_smells)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=bugs)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=duplicated_lines_density)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=sqale_index)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=ncloc)](https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity)

MC Discord Activity - мод для Minecraft 1.7.10, который в активностях дискорда устанавливает кастомный текст с
картинкой, а также меняет иконку и заголовок окна майнкрафта. Полезно для серверов.

## Общая информация

Этот репозиторий - проект Gradle, который должен быть открыт через IntelliJ IDEA.

| Технология                             | Версия |
|----------------------------------------|--------|
| Система автоматической сборки Gradle   | 8.5    |
| Java, используемая для запуска Gradle  | 8+     |
| Java, используемая для сборки проекта  | 8      |
| Java, используемая для запуска проекта | 8      |

## Установка

Установка моих проектов Gradle и основы работы с ними примерно одинаковы, так что
смотрите [общую инструкцию](https://github.com/Hummel009/Legendary-Item#readme).

## Использование

Инструкция пригодится как программистам, так и игрокам, просто программисты могут некоторые пункты выполнить иначе.

* Установите мод и откройте его при помощи WinRar. Первым делом вы увидите там файл настройки `.properties`, который
  можно открыть блокнотом.
* Открыв его, вы увидите там идентификатор, два логотипа, два названия на английском и три закодированных названия на
  русском.
    * Про названия на русском и английском говорить нечего, потому что их смысл станет очевиден, когда вы запустите игру
      и посмотрите в свой профиль в дискорде.
    * Чтобы редактировать русские слова в таком же духе,
      используйте [конвертер туда и обратно, в символы и в Java Unicode](https://r12a.github.io/app-conversion/).
    * Логотипы и идентификатор - это [ваше приложение в дискорде](https://discord.com/developers/applications). Создать
      его может любой желающий и программировать там ничего не надо. Копируем его идентификатор, загружаем в него два
      фото и указываем их имена в файле настроек мода. Profit!
        * Кэширование может занять пять или десять минут, так что загружайте фото с первого раза и терпеливо ожидайте.
* Иконка майнкрафта меняется по пути assets/mcda/icons.
