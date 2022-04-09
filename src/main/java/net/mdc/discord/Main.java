package net.mdc.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static final Logger LOGGER = LogManager.getRootLogger();
    private static JDA jda;

    public static void main(String[] args) {
        final String token = System.getProperty("DISCORD_TOKEN");
        final JDABuilder jdaBuilder;
        final Scanner scanner;
        String line;

        if (token == null) {
            LOGGER.error("Missing token.");
            System.exit(22);
        }
        LOGGER.info("Starting...");
        jdaBuilder = JDABuilder.create(Arrays.asList(GatewayIntent.values()));
        jdaBuilder.setToken(token);
        jdaBuilder.setAutoReconnect(true);
        jdaBuilder.enableCache(Arrays.asList(CacheFlag.values()));
        try {
            jda = jdaBuilder.build();
        } catch (LoginException exception) {
            LOGGER.error("JDA Error.", exception);
            System.exit(103);
        }
        scanner = new Scanner(System.in);
        try {
            while ((line = scanner.nextLine()) != null);
        } catch (NoSuchElementException ignored) {}
        LOGGER.info("Closing...");
        if (jda == null)
            return;
        jda.shutdown();
    }

    public static JDA getJda() {
        return jda;
    }

}
