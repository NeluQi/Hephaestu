/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.vkhephaestusbot.Command.ManageChat;

import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import java.io.File;
import java.io.IOException;

public class ChatData {

    public static void ChatData(Message msg) throws ApiException, ClientException, IOException {
        String baseDir = new File(".").getCanonicalPath();
        File folderDate = new File(baseDir + "/chats");
        if (!folderDate.exists()) {
            folderDate.mkdir();
        }

        if (msg.getChatId() != null) {
            folderDate = new File(baseDir + "/chats/" + msg.getChatId());
            if (!folderDate.exists()) {
                folderDate.mkdir();
                if (!new File(baseDir + "/chats/" + msg.getChatId() + "/data.ini").exists()) {
                    new File(baseDir + "/chats/" + msg.getChatId() + "/data.ini").createNewFile();
                }
                System.out.println("[ChatData]New data folder");
                LOG.info("[ChatData]New data folder");
            }
        }

    }
}
