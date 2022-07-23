# Magma Cube Taming Plugin
Whenever a Magma Cube hits (sniffs) a player, it has a 33% chance of being tamed. Once tamed, the Magma Cube will follow the player and won't be able to deal any damage.

## Commands
- /magma: This toggles if Magma Cubes should be able to be tamed. By default, Magma Cubes can be tamed. (Players must be OP to use this command).

## How to Use
1. Run the following commands in a command prompt or terminal
    1. If on Windows:
        1. `gradlew clean`
        2. `gradlew build`

    2. If on macOS/Linux
        1. `chmod +x gradlew`
        2. `./gradlew clean`
        3. `./gradlew build`
2. The JAR file for the plugin will be located in `build\libs\`. Simply move the JAR file into the plugins folder, and the plugin will start working when the server is started.

### Dependencies
- Spigot (https://www.spigotmc.org/)
- JSON-Simple (https://github.com/fangyidong/json-simple)