<div align="center">

# Apollo Client

[![Discord](https://img.shields.io/discord/740991579342503936?color=informational&label=%20&logo=discord&logoColor=white&style=for-the-badge)](https://discord.gg/mAx8Phh)

</div>

## About
Apollo is a Hypixel oriented pvp client for 1.8.9 that uses the [Sponge Mixin API](https://github.com/SpongePowered/Mixin) to hook into game events and inject into Minecraft. Apollo will have countless modules enjoyed by the forge community as well as an SDK to develop and profit off your own modules and cosmetics!

### Forum Post

If you create any forum post for the client put the link below so we can keep track of our social presence.

- https://hypixel.net/threads/apollo-client-discord-new-hypixel-client.3219735/#post-22502168
- https://hypixel.net/threads/apollo-client-what-is-apollo-client-unique-1-8-9-pvp-client-for-hypixel-more.3304557/

## Development Setup

You will first need to set up a development workspace with the Apollo source code. You can do this by downloading the repository, opening the `build.gradle` as a project in IntelliJ IDEA and running the following commands from terminal or gradle window.

```bash
./gradlew setupDecompWorkspace
./gradlew genIntellijRuns
```

![Image of project](https://media.discordapp.net/attachments/747901986770518047/750929662795972740/Screen_Shot_2020-09-02_at_11.06.15_PM.png)

This will download the dependencies and setup the run configuration for the project allowing you to run the client from your IDE. If the game will still not run after you have completed these steps make sure the `Minecraft Client` run configuration is set to the module `Apollo.main`.

![Apollo.main](https://media.discordapp.net/attachments/747901986770518047/750931022694318120/Screen_Shot_2020-09-02_at_11.11.31_PM.png)
