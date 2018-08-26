/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bot.vkhephaestusbot;

import com.bot.vkhephaestusbot.Auxiliary.VoteList;
import static com.bot.vkhephaestusbot.LongPoll.SessionVoteList;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import java.io.IOException;
import java.text.ParseException;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import static com.bot.vkhephaestusbot.Main.ACTOR;

/**
 *
 * @author Nelu
 */
public class TimeEvent {
    public static void TimeEvent() throws IOException, ParseException, ApiException, ClientException{
    CheakVotes();
    } 
    
    public static void CheakVotes() throws ApiException, ClientException{
        //TODO Сделать нормально
        // ну или хуй с ним, лень делать
        int rm = -1;
        for(VoteList Vote : SessionVoteList){
            int index = 0;
                if (Vote.deadline.getTime() < System.currentTimeMillis()){
                       String WIN = "";
                       if(Vote.yes > Vote.no){WIN = "Результат голосования - ДА!";}
                       if(Vote.yes < Vote.no){WIN = "Результат голосования - НЕТ!";}
                       if(Vote.yes == Vote.no){WIN = "Мнение разделилось! Ничья";}
                       APICLIENT.messages().send(ACTOR).chatId(Vote.chatID).message("Голосование окончено! \n"
                              + "Проголосовало " + (Vote.yes + Vote.no) + " из " + Vote.CountUser
                              + "("+ Math.round((((Vote.yes + Vote.no)*100)/Vote.CountUser)) + "%)\n"
                              + WIN).execute(); System.out.println("Send msg Голосование окончено!");
                         rm = index;
                         break;
          }
       index++;
       } 
        if(rm != -1){SessionVoteList.remove(rm);}
    }
}