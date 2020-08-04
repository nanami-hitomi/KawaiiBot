# KawaiiBot / Awooo
Kawaiibot reworked for NieR: Automata speedrun discord.


### Build and run, Windows
```
gradlew.bat build
gradlew.bat run
```

### Build and run, Docker
If you prefer you can run a dockerized version of the bot:

#### Build docker image from sources
```
$ docker build -t kawaiibot .  
```

#### Run docker image on container named "kbot"
```
$ docker run --publish 8000:8080 --detach --name kbot kawaiibot:latest
```

#### Optional: Show log stream
```
$ docker logs -f kbot
```

#### Kill container
```
$ docker container stop kbot && docker container rm kbot
```
