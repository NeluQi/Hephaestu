package com.bot.vkhephaestusbot;

import static com.bot.vkhephaestusbot.Command.FakeFiles.FakeFiles;
import static com.bot.vkhephaestusbot.Command.HelperBOT.HelperBOT;
import static com.bot.vkhephaestusbot.Command.ManageChat.AddFriend.AddFriend;
import static com.bot.vkhephaestusbot.Command.ManageChat.ChatData.ChatData;
import static com.bot.vkhephaestusbot.Command.ManageChat.InviteUserChat.InviteUserChat;
import static com.bot.vkhephaestusbot.Command.ManageChat.JoinChatLink.Join;
import static com.bot.vkhephaestusbot.Command.TextAnswers.TextAnswers;
import static com.bot.vkhephaestusbot.Command.Vote.Vote;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ResponseProcessing {

    public static void ResponseProcessing(Message msg) throws ApiException, ClientException, IOException, FileNotFoundException, ParseException, ParserConfigurationException, SAXException{
        LOG.info("msg: "); LOG.info(msg.getBody());
        System.out.println("msg: " + msg.getBody());
        
        //Modules
        //Идея такова. Новый евент (Сообщение, событие и тд) --> Попадаем сюда. Всё инфа о событие в msg --> Прогоняем по модулям, а в них делаем нужные проверки и действия. 
             //general
            ChatData(msg);
            AddFriend(msg);
            InviteUserChat(msg); // event new user in chat
            TextAnswers(msg);
            HelperBOT(msg);
            Vote(msg);
            FakeFiles(msg); // Антидедлайн — битые файлы
            Join(msg); // вход в чат по ссылки

    }
}
