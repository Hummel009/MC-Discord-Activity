> [!CAUTION]
> С 29.02.2024 разработка проекта завершена. Инструментарий будет поддерживаться в актуальном и рабочем состоянии, но
> правки в основной код вноситься не будут.

[![Code Smells][code_smells_badge]][code_smells_link]
[![Maintainability Rating][maintainability_rating_badge]][maintainability_rating_link]
[![Security Rating][security_rating_badge]][security_rating_link]
[![Bugs][bugs_badge]][bugs_link]
[![Vulnerabilities][vulnerabilities_badge]][vulnerabilities_link]
[![Duplicated Lines (%)][duplicated_lines_density_badge]][duplicated_lines_density_link]
[![Reliability Rating][reliability_rating_badge]][reliability_rating_link]
[![Quality Gate Status][quality_gate_status_badge]][quality_gate_status_link]
[![Technical Debt][technical_debt_badge]][technical_debt_link]
[![Lines of Code][lines_of_code_badge]][lines_of_code_link]

MC Discord Activity - мод для Minecraft 1.7.10, который в активностях дискорда устанавливает кастомный текст с
картинкой, а также меняет иконку и заголовок окна майнкрафта. Полезно для серверов.

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

<!----------------------------------------------------------------------------->

[code_smells_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=code_smells

[code_smells_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[maintainability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=sqale_rating

[maintainability_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[security_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=security_rating

[security_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[bugs_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=bugs

[bugs_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[vulnerabilities_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=vulnerabilities

[vulnerabilities_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[duplicated_lines_density_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=duplicated_lines_density

[duplicated_lines_density_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[reliability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=reliability_rating

[reliability_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[quality_gate_status_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=alert_status

[quality_gate_status_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[technical_debt_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=sqale_index

[technical_debt_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity

[lines_of_code_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_MC-Discord-Activity&metric=ncloc

[lines_of_code_link]: https://sonarcloud.io/summary/overall?id=Hummel009_MC-Discord-Activity
