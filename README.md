
# SET Game Server


## Russian

Название игры «Set» переводится как «набор» – суть игры заключается в обнаружении разрешённых комбинаций карт (наборов) среди двенадцати карт, выложенных на стол. В отличие от многих других игр в Сете все игроки играют одновременно – задача каждого из них первым обнаружить набор.

### Разработка 

Сервер был разработан на языке программирования [Java](https://www.java.com/ru/) с применением микрофреймворка [Spark](https://sparkjava.com/). Отладка проводилась через утилиту [Insomnia REST](https://insomnia.rest/),
Итоговое тестирование сервера проводилось через [разработанную для этого утилиту](https://github.com/vladnov138/set-testclient)

### Разработанный функционал

1) Поддерживается только одна игровая комната
2) Реализованы авторизация, регистрация
3) Реализованы все запросы, описанные в [протоколе](https://github.com/Krushiler/com.krushiler.set-game-server/blob/master/Readme.md)
4) Реализована система подсказок в консоль сервера (не баг, а фича!) 
5) Обрабатываются все возможные ситуации






## English

The name of the game "Set" is translated as "set" - the essence of the game is to find the allowed combinations of cards (sets) among the twelve cards laid out on the table. Unlike many other games in the Set, all players play at the same time - the task of each of them is to be the first to discover the set.

### Development

The server was developed in the [Java programming language](https://www.java.com/ru/) using the [Spark microframework](https://sparkjava.com/). Debugging was carried out through the [Insomnia REST](https://insomnia.rest/),
The final testing of the server was carried out through [a utility developed for this](https://github.com/vladnov138/set-testclient)

### Developed functionality

1) Only one game room is supported
2) Implemented authorization, registration
3) Implemented all requests described in [protocol](https://github.com/Krushiler/com.krushiler.set-game-server/blob/master/Readme.md)
4) Implemented a hint system in the server console (not a bug, but a feature!)
5) All possible situations are handled
