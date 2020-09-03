[![Discord](https://canary.discordapp.com/api/guilds/740991579342503936/widget.png) ](https://discord.gg/QWAunms)

# Apollo

Apollo is an Hypixel oriented pvp client for 1.8.9 that uses the [SpongeAPI](https://www.spongepowered.org) to hook into game events and inject into Minecraft. Apollo will have countless modules enjoyed by the forge community aswell as a SDK to develop and profit off youre own modules and cosmetics!

- [Development Setup](#development-setup)
- [Contribution Guidlines](#contribution-guidlines)
- [License / Copyright](#copyright)

### Checklist:

- [x] Module Backend
- [ ] Event Backend
- [ ] Addon System
- [ ] Hypixel Api Wrapper
- [ ] Main Module GUI
- [ ] Color Codes / Text Wrap in FontRenderer

### Events:

All Events the are added to apollo must be documented below to help developers in future sdk. You must fill out the name of the event class and a description of when it is called.

| Event | Event Description |
| --- | --- |
| example | example description|

### Modules:

All Modules must be documented aswell. When adding any new modules create a new line to the table with the module name and a description of what it does.

| Module | Moudle Description |
| --- | --- |
| example | example description|

### Forum Post:

If you create any forum post for the client put the link below so we can keep track of out social presense.

- https://hypixel.net/threads/apollo-client-discord-new-hypixel-client.3219735/#post-22502168
- https://hypixel.net/threads/apollo-client-sdk-update-for-apollo-client-development.3292568/#post-23178676

# Development Setup

First you will need to setup a development workspace with the Apollo source code. You can do this by downloading the repository, opening the `build.gradle` as a project in intelij and running the following commands from terminal or gradle window.

```bash
./gradlew setupDecompWorkspace
./gradlew genIntiljRuns
```

![Image of project](https://media.discordapp.net/attachments/747901986770518047/750929662795972740/Screen_Shot_2020-09-02_at_11.06.15_PM.png)

This will download the dependencies and setup the run configuration for the project allowing you to run the client from your IDE. If the game will still not run after you have completed these steps make sure `Minecraft Client` run configuration is set to the module `Apollo.main`.

![Apollo.main](https://media.discordapp.net/attachments/747901986770518047/750931022694318120/Screen_Shot_2020-09-02_at_11.11.31_PM.png)

# Contribution Guidlines

To stay organized with the rest of the team and keep a consistent code base I have laid out a few formatting rules all Apollo source must follow to be implemented. For a more in depth look into the code style we are looking for check out classes that have already been implimented and try and keep it mostly the same.

### Classes
All classes should be headed with a short description of the class purpose, the author (with contact) and the version it was implemented in. As you can see below descriptions are compact and written with correct punctuation.

```java
/** MixinBootstrap events for Keybinding.class.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
@Mixin(KeyBinding.class) public class MixinKeybinding {

}

/** Various functions to draw gui objects using GL11 and WorldRenderer.
 * @author Icovid | Icovid#3888
 * @since 1.0.0 **/
public class GLRenderer {

}
```

### Variables
Variables should be split in groups based on functionality and be given descriptions if applicable. Name depends on the type of variable but try and stay close to the format below.

```java
    // Public fonts used in Apollo Guis.
    public static String ROBOTO = "/fonts/Roboto-Regular.ttf";
    public static String ROBOTO_MEDIUM = "/fonts/Roboto-Medium.ttf";

    public final String name;
    public final String description;
    public final Category category;

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo INSTANCE = new Apollo();
    public final ModuleManager moduleManager;
    public final SettingManager settingManager;
```

### Functions
all functions should have a correctly punctuated description and `@param` description for each in all lowercase. A `@return` description should be provided in same way if applicable. Functions should not contain `@author` and should be compact like shown below.

```java 
     /** {@link GL11} scissor using minecraft screen positions.
     * @param xPosition X start location
     * @param yPosition Y start location
     * @param width scissor width
     * @param height scissor height **/
      public static void glScissor(double xPosition, double yPosition, double width, double height) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glScissor((int) ((xPosition * Minecraft.getMinecraft().displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - (yPosition + height)) * Minecraft.getMinecraft().displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * Minecraft.getMinecraft().displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * Minecraft.getMinecraft().displayHeight / scaledResolution.getScaledHeight()));
    }
     
```

### Event Functions
Event functions are similar to most functions implemented in Apollo with one key difference. You must provide a description, each param correctly formatted and a link or `@see` to the Event class.

```java
    /** Called on every key press.
     * @param key key code of key pressed 
     * @see io.Apollo.event.impl.KeybindEvent **/
    @Inject(method = "onTick", at = @At("HEAD"))
    private static void keyHook(int key) {
        new KeybindEvent.call(key);
    }
```

### Copyright
All classes should include the Apollo copyright from below. It will be ideal to set a copoyright profile in intlij to this so it will be automatically imputed to each class. However this isn't mandatory and can easily be implimented once merged with main branch so don't be to worried about it.

```
/*⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤
Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

$file.fileName is part of Apollo Client. $today

$file.fileName can not be copied and/or distributed without the express
permission of Icovid

Contact: Icovid#3888 @ https://discord.com
⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤⏤*/
```

![show](https://media.discordapp.net/attachments/747901986770518047/750940281657884702/Screen_Shot_2020-09-02_at_11.48.16_PM.png?width=1500&height=859)
