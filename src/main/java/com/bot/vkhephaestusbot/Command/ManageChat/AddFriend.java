package com.bot.vkhephaestusbot.Command.ManageChat;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

public class AddFriend {
 public static void AddFriend(Message msg) throws ApiException, ClientException{
        if((msg.getBody().matches("/add") || msg.getBody().matches("/a")) && msg.getChatId() == null) {
            APICLIENT.messages().send(ACTOR).userId(msg.getUserId()).message("Если не сработало, попробуй добавить в меня друзья и ещё раз ввести эту команду").execute();
            APICLIENT.friends().add(ACTOR, msg.getUserId()).execute();
            } 
    }
}
