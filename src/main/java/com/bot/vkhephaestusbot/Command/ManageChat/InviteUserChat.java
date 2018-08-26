package com.bot.vkhephaestusbot.Command.ManageChat;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import static com.vk.api.sdk.objects.messages.Action.CHAT_INVITE_USER;
import com.vk.api.sdk.objects.messages.Message;
import java.util.Objects;

public class InviteUserChat {

    public static void InviteUserChat(Message msg) throws ApiException, ClientException {
        if (msg.getAction() == CHAT_INVITE_USER && !Objects.equals(msg.getActionMid(), ACTOR.getId())) {
            LOG.info("[InviteUserChat]CHAT_INVITE_USER");
            APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("Hello user!").execute();
            System.err.println("Send MSG");
        }
        if (msg.getAction() == CHAT_INVITE_USER && Objects.equals(msg.getActionMid(), ACTOR.getId())) {
            APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("Для корректной работы, следует назначить бота администратором.").execute();
        } 
    }
}
