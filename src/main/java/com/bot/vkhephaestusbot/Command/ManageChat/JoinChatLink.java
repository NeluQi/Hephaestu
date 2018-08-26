/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.vkhephaestusbot.Command.ManageChat;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

/**
 *
 * @author Nelu
 */
public class JoinChatLink {
    public static void Join(Message msg) throws ApiException, ClientException{
    if(msg.getBody().matches("(^https:\\/\\/vk\\.me\\/join\\/(.*))") && msg.getChatId() == null) {
                APICLIENT.messages().joinChatByInviteLink(ACTOR, msg.getBody()).execute(); 
                LOG.info("[JoinChatLink] Join chat");  
            }
    }
}
