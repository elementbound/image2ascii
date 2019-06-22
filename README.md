# image2ascii #

A CLI tool to convert images to ASCII art.

## Demo ##

| Ascii 16                        | Ascii 256                         | Original                       |
|---------------------------------|-----------------------------------|--------------------------------|
| ![Ascii16](assets/wizard16.png) | ![Ascii256](assets/wizard256.png) | ![Original](assets/wizard.png) |

## Install ##

To use the application, you must first build it with Maven:

```sh
mvn clean install
```

This will create a runnable JAR file in `cli/target`

## Usage ##

For convenience, a shell script is available to run the built JAR:

```txt
$ ./image2ascii --help

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.4.RELEASE)

2019-06-22 23:36:22.970  INFO 13380 --- [           main] c.g.e.asciima.image2ascii.cli.App        : Starting App v1.0-SNAPSHOT on Hades with PID 13380 (E:\dev\java\asciima\image2ascii\cli\target\image2ascii-cli-1.0-SNAPSHOT.jar started by adminus in E:\dev\java\asciima\image2ascii)
2019-06-22 23:36:22.973  INFO 13380 --- [           main] c.g.e.asciima.image2ascii.cli.App        : No active profile set, falling back to default profiles: default
2019-06-22 23:36:24.421  INFO 13380 --- [           main] c.g.e.asciima.image2ascii.cli.App        : Started App in 1.891 seconds (JVM running for 2.952)
Usage: image2ascii [-hV] [-cm=<colorMapperType>] [-cs=<characterSet>]
                   [-cw=<characterWeightFunctionType>] [-p=<palette>] input
      input                 Input file
      -cm, --colorMapper=<colorMapperType>
                            Possible values: NEAREST, FUZZY
      -cs, --characterSet=<characterSet>
                            Possible values: GRADIENT, EXTENDED_GRADIENT
      -cw, --characterWeight=<characterWeightFunctionType>
                            Possible values: HIT_MISS, GRADIENT
  -h, --help                Show this help message and exit.
  -p, --palette=<palette>   Possible values: ASCII_16, ASCII_256
  -V, --version             Print version information and exit.

```
