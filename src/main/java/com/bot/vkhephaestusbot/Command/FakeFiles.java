package com.bot.vkhephaestusbot.Command;

import com.bot.vkhephaestusbot.Main;
import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

// МОДУЛЬ  "Антидедлайн — битые файлы"

public class FakeFiles {
    public static void FakeFiles(Message msg) throws ApiException, ClientException{
            if(msg.getBody().matches("/fake") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("Антидедлайн — битые файлы\n"
                        + "/fake docx - Получить битый docx\n"
                        + "/fake pptx - Получить битый pptx\n"
                        + "/fake xlsx - Получить битый xlsx\n"
                        + "/fake psd - Получить битый psd\n"
                        + "/fake pdf - Получить битый pdf\n").attachment().execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
            
                if(msg.getBody().matches("/fake xlsx") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("").attachment("doc501549239_472361082").execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
                
                if(msg.getBody().matches("/fake psd") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("").attachment("doc501549239_472361074").execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
                
                if(msg.getBody().matches("/fake pptx") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("").attachment("doc501549239_472361070").execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
                
                if(msg.getBody().matches("/fake pdf") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("").attachment("doc501549239_472361046").execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
                
                if(msg.getBody().matches("/fake docx") && msg.getChatId() != null) {
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("").attachment("doc501549239_472361026").execute();
                System.out.println("[FakeFiles]Send message"); LOG.info("[FakeFiles]Send message");}
    }  
}
