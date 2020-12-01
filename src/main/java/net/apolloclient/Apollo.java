/*⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼
  Copyright (C) 2020-2021 developed by Icovid and Apollo Development Team

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.
  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see https://www.gnu.org/licenses/.

  Contact: Icovid#3888 @ https://discord.com
 ⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼⎼*/

package net.apolloclient;


import net.apolloclient.command.CommandBus;
import net.apolloclient.event.bus.EventBus;
import net.apolloclient.event.bus.SubscribeEvent;
import net.apolloclient.event.impl.client.input.KeyPressedEvent;
import net.apolloclient.gui.ModulesGUI;
import net.apolloclient.mixins.ApolloTweaker;
import net.apolloclient.module.bus.ModContainer;
import net.apolloclient.module.bus.ModuleFactory;
import net.apolloclient.module.bus.event.PostInitializationEvent;
import net.apolloclient.utils.font.ApolloFontRenderer;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main class used to hold all aspects of the client and startup
 * any managers and containers listed below.
 *
 * <li> {@link ModuleFactory} : constructs modules and register EventHandlers. </li>
 *
 * @author Icovid | Icovid#3888
 * @since b0.2
 */
public class Apollo {

    // Apollo information used for display title and credits info.
    public static final String NAME = "Apollo", VERSION = "b0.2";

    // Public client instance used to retrieve any aspect of Apollo.
    public static final Apollo INSTANCE = new Apollo();

    public static final EventBus EVENT_BUS = new EventBus();
    public static final CommandBus COMMAND_BUS = new CommandBus();
    public static final ModuleFactory MODULE_FACTORY = new ModuleFactory("net.apolloclient.module.impl", new File(ApolloTweaker.gameDir.getAbsolutePath() + "/mods"));

    /**
     * Called on Minecraft startup.
     */
    public void initialization() {
        EVENT_BUS.register(COMMAND_BUS);
        EVENT_BUS.register(this);

        try { ApolloFontRenderer.loadEmojis();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void postInitialization() {
        for (ModContainer container : MODULE_FACTORY.modules)
            container.post(new PostInitializationEvent(container));
    }

    /**
     * Called on Minecraft shutdown.
     */
    public void stopClient() {
    }

    /**
     * Used to log Apollo messages to console
     *
     * @param message any string to be displayed in console.
     */
    public static void log(String... message) {
        for (String out : message)
            System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo] " + out);
    }

    /**
     * Used to log Apollo errors to console.
     *
     * @param message any string to be displayed in console.
     */
    public static void error(String... message) {
        for (String out : message)
            System.err.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] [Apollo-Error] " + out);
    }

    @SubscribeEvent
    public void test(KeyPressedEvent event) {
        if (event.keyCode == Keyboard.KEY_RSHIFT)
            Minecraft.getMinecraft().displayGuiScreen(new ModulesGUI());
    }

}
