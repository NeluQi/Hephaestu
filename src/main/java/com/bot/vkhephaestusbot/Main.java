package com.bot.vkhephaestusbot;

import static com.bot.vkhephaestusbot.Authorization.Auth;
import static com.bot.vkhephaestusbot.Authorization.Check;
import static com.bot.vkhephaestusbot.Helper.ShowHelpCommand;
import static com.bot.vkhephaestusbot.LogSettings.LogSettings;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {
    public static final Logger LOG = Logger.getGlobal();
    public static boolean stopBOT = true; //Остановка бота
    public static final VkApiClient APICLIENT = new VkApiClient(new HttpTransportClient()); //Глобальный VkApiClient
    public static UserActor ACTOR; //Глобальный UserActor

    private static boolean stop = false; //Остановка ввода

    public static void main(String[] args) throws ApiException, ClientException, InterruptedException, FileNotFoundException, IOException {
       LogManager.getLogManager().readConfiguration(LogSettings()); // Загрузка настройки логирования. Настройки в LogSettings, мне так удобнее.
       Scanner scanner = new Scanner(System.in);
        do { //Проверка аккаунта бота
        if(!Check()){
                    System.err.println("Enter information from your bot account");
                    System.out.print("Login:");
                    String login = scanner.nextLine();
                    System.out.print("Password:");
                    String password = scanner.nextLine();
                    Auth(login, password);
                }
        } while (!Check());
       
        //Command line
        System.out.println("\n Hello! I'm Hephaestus bot \n help - show all command");
         while(!stop){
            System.out.println(""); System.out.print(">>> "); 
            String command = scanner.nextLine(); System.out.println("");
            if(command.matches("run")) {LOG.info("Start bot..."); System.out.println("Start bot..."); runBot();}
            if(command.matches("stop")) {stopBOT=true; System.out.println("Bot stoping...");}
            if(command.matches("is")) {if(stopBOT){System.out.println("Bot OFF");}else{System.out.println("Bot ON!");}}
            if(command.matches("help")) {ShowHelpCommand();}
            if(command.matches("exit")) {stop= true; stopBOT=true;}
         }
        LOG.info("Exit..."); System.out.println("Exit...");
    }

    private static void runBot() {
        stopBOT = false;
        LOG.info("Run Thread...");
        Runnable RLongPoll = new LongPoll(APICLIENT, ACTOR);
        new Thread(RLongPoll).start();

        int MINUTES = 1; // The delay in minutes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOG.info("Check!");
                System.out.println("Check!");
                try {
                    TimeEvent.TimeEvent();
                } catch (IOException | ParseException | ApiException | ClientException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000 * 60 * MINUTES);
    }
}
