package com.bot.vkhephaestusbot;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//НАСТРОЙКИ ЛОГИРОВАНИЯ
public class LogSettings {
    public static InputStream LogSettings(){
    String initialString = ".level=INFO \n" +
            "handlers=java.util.logging.ConsoleHandler \n" +
            "java.util.logging.ConsoleHandler.level=FINEST \n" +
            "deng.level=FINEST \n" +
            "\n" +
            "handlers= java.util.logging.FileHandler\n" +
            "\n" +
            "java.util.logging.FileHandler.pattern = log.log\n" +
            "java.util.logging.FileHandler.limit = 5000000\n"
            + "java.util.logging.SimpleFormatter.format=[%1$tF %1$tT] [%4$-7s] %5$s %n\n"
            + "java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter\n"
            + "java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter\n";
        return new ByteArrayInputStream(initialString.getBytes());
    }
}
