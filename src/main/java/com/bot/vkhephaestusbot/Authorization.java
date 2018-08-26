package com.bot.vkhephaestusbot;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.account.Info;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

public class Authorization {

    public static void Auth(String login, String password) { //Получаем access_token через логин\пароль и сохраняем
        try {
            HttpTransportClient Client = new HttpTransportClient();
            String url;
            url = "https://oauth.vk.com/token?grant_type=password&client_id=3140623&client_secret=VeWdmVclDCtn6ihuP1nt&username=" + login + "&password=" + password + "&scope=offline,friends,messages,wall,notify,photos,audio,video,docs,notifications,stats";
            URLConnection con = new URL(url).openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(body).getAsJsonObject();
            Properties Property = new Properties();
            Property.load(new FileInputStream("config.ini"));
            Property.setProperty("access_token", mainObject.get("access_token").toString().replace("\"", ""));
            Property.setProperty("userid", mainObject.get("user_id").toString());
            Property.store(new FileOutputStream("config.ini"), null);
        } catch (JsonSyntaxException | IOException ex) {
            System.err.println("=================");
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error auth");
            System.err.println("=================");
        }

    }

    public static boolean Check() { //Проверяет и загружает аккаунт
        try {
            Properties Property = new Properties();
            if (!new File("config.ini").exists()) {
                new File("config.ini").createNewFile();
            }
            Property.load(new FileInputStream("config.ini"));
            String access_token = Property.getProperty("access_token");
            String userid = Property.getProperty("userid");
            ACTOR = new UserActor(Integer.parseInt(userid), access_token);
            Info check = APICLIENT.account().getInfo(ACTOR).execute();
            return true;
        } catch (ApiException | ClientException | IOException | NumberFormatException ex) {
            System.err.println("Error authorization");
            return false;
        }
    }
}
