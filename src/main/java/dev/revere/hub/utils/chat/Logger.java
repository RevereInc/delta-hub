package dev.revere.hub.utils.chat;

import dev.revere.hub.DeltaHub;
import org.bukkit.Bukkit;

/**
 * @author Remi
 * @project DeltaHub
 * @date 5/27/2024
 */
public class Logger {

    /**
     * Log the time it takes to run a task.
     *
     * @param taskName the name of the task
     * @param runnable the task to run
     */
    public static void logTime(String taskName, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + "&fSuccessfully loaded &b" + taskName + " &fin &b" + (end - start) + "ms&f."));
    }

    /**
     * Log a message to the console.
     *
     * @param message the message to log
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + message));
    }

    /**
     * Log an error to the console.
     *
     * @param message the error message to log
     */
    public static void logError(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + "&4ERROR: " + message + "!"));
    }

    /**
     * Log an error to the console and broadcast in chat.
     *
     * @param message the error message to log
     */
    public static void broadcastError(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(CC.translate(CC.getPrefix() + "&4ERROR: " + message + "!"));
        Bukkit.broadcastMessage(CC.translate(CC.getPrefix() + "&cERROR: " + message + "!"));
    }

    /**
     * Log an exception to the console.
     *
     * @param exception the exception to log
     */
    public static void logException(Exception exception) {
        logError("&cAn exception occurred: " + exception.getMessage());
    }

    /**
     * Log an exception to the console and broadcast in chat.
     *
     * @param exception the exception to log
     */
    public static void broadcastException(Exception exception) {
        broadcastError("&cAn exception occurred: " + exception.getMessage());
    }

    /**
     * Log an exception to the console with the stack trace.
     *
     * @param exception the exception to log
     */
    public static void logExceptionWithStackTrace(Exception exception) {
        logError("&cAn exception occurred: " + exception.getMessage());
        logError("&cStack trace:");
        logError("");
        exception.printStackTrace();
        logError("");
    }
}