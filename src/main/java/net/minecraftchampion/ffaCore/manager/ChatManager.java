package net.minecraftchampion.ffaCore.manager;

import net.minecraftchampion.ffaCore.FFACore;

public class ChatManager {

    /**
     * Send a message in the console
     *
     * @param message Message
     */
    public static void sendConsoleMessage(String message) {
        FFACore.LOGGER.info("[" + FFACore.PLUGIN_NAME + "] " + message);
    }

    /**
     * Send a warning in the console
     *
     * @param message Message
     */
    public static void sendWarnMessage(String message) {
        FFACore.LOGGER.warning("[" + FFACore.PLUGIN_NAME + "] " + message);
    }

}