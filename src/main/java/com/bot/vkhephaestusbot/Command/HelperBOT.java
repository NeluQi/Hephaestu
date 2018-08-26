package com.bot.vkhephaestusbot.Command;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public class HelperBOT {
     public static void HelperBOT(Message msg) throws ApiException, ClientException{
         //help in chat
         if(msg.getBody().matches("/help") || msg.getBody().matches("!help") || msg.getBody().matches(".help") || msg.getBody().matches(".рудз") || msg.getBody().matches("/рудз") || msg.getBody().matches("/h")){
             if(msg.getChatId() != null){
                APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("Команды:\n"
                        + "/help - Список комманд\n"
                        + "/fake - Антидедлайн — битые файлы\n"
                        + "/vote \"<ВОПРОС>\" <КОЛ-ВО МИНУТ НА ГОЛОСОВАНИЕ> - запустить голосование\n"
                        + "/rdm - Случайный ответ (Пример сокращенный комманды: '/r Идти в магазин?' )\n"
                        + "/pron - ???\n"
                        + "/hh - ???\n"
                        ).execute(); 
                System.out.println("[HelperBOT]Send message"); LOG.info("[HelperBOT]Send message");
             }
          // help in LS
          if(msg.getChatId() == null && msg.getBody().matches("/help")){
             
             }
         
         
         }
 

            
        
    }
}
