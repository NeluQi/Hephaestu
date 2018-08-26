package com.bot.vkhephaestusbot.Command;

import com.bot.vkhephaestusbot.Auxiliary.VoteList;
import static com.bot.vkhephaestusbot.LongPoll.SessionVoteList;
import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vote {
     public static void Vote(Message msg) throws ApiException, ClientException, FileNotFoundException, IOException, ParseException{
            final String regex = "^[\\s]*(\\/vote)[\\s]*\"(.*?)\"[\\s]*([\\d]*)$";
            final Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
            final Matcher matcher = pattern.matcher(msg.getBody());
            if (msg.getChatId() != null && matcher.find( )) {
                       VoteList Vote = new VoteList();
                       Vote.chatID = msg.getChatId();
                       Vote.question = matcher.group(2);
                       Vote.deadline = addMinutesToDate(Integer.parseInt(matcher.group(3)), new Date());
                       Vote.no = 0;
                       Vote.yes = 0;
                       Vote.CountUser = msg.getUsersCount(); 
                       Vote.FilterUser = "";
                       SessionVoteList.add(Vote);
                       APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("Внимание! Голосование!\n"
                               + matcher.group(2) + "\n"
                               + "Напиши /yes чтобы проголосовать ЗА\n"
                               + "Напиши /no чтобы проголосовать ПРОТИВ\n"
                               + "На голосование отведено " + matcher.group(3) + " минут\n"
                               + "Узнать информацию о голосование - /show vote\n").execute(); System.err.println("Send msg VOTE");
            } 
            
            List<VoteList> Sessionsdst = SessionVoteList;
            VoteUser(msg, ACTOR, APICLIENT);
    }
     
     public static void  VoteUser(Message msg, UserActor actor, VkApiClient apiClient) throws ApiException, ClientException{
         //TODO рефоракторинг 
            yes(msg, actor, apiClient); // Command /yes
            no(msg, actor, apiClient); //Comand /no
            
            if(msg.getBody().matches("/show vote") && msg.getChatId() != null){
                for(VoteList Vote : SessionVoteList){
                    if(msg.getChatId() == Vote.chatID){
                        long diff = System.currentTimeMillis() - Vote.deadline.getTime();;
                        long diffMinutes = diff / (60 * 1000) % 60;
                        apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование идет!\n"
                               + "Вопрос: " + Vote.question
                               + "\nПроголосовало ЗА - "+ Vote.yes
                               + "\nПроголосовало ПРОТИВ - " + Vote.no
                               + "\nПроголосовало " + (Vote.yes + Vote.no) + " из " + Vote.CountUser
                               + "("+ Math.round((((Vote.yes + Vote.no)*100)/Vote.CountUser)) + "%)\n"
                               + "\nДо конца голосования осталось " + Math.abs(diffMinutes)
                               + " минут(ы)"
                                ).execute(); System.out.println("Send msg /show vote");} 
                    else {apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование не найдено!\n").execute(); System.out.println("Send msg Голосование не найдено!");}
                } 
                 
            }
     
     }
     
     private static void yes(Message msg, UserActor actor, VkApiClient apiClient) throws ApiException, ClientException{
                if(msg.getBody().matches("/yes") && msg.getChatId() != null){
                            VoteList updateVote = new VoteList();
                            int index = 0;
                            int IndexUpdateVote = -1;
                            boolean FILTER = false;
                            for(VoteList Vote : SessionVoteList){
                               if(msg.getChatId() == Vote.chatID){
                                   for(String FilterUser : Vote.FilterUser.split(",")){
                                       if(Objects.equals(msg.getUserId().toString(), FilterUser)){FILTER = true;
                                           apiClient.messages().send(actor).chatId(msg.getChatId()).message("Ты уже голосовал!\n").execute();}}
                                   if(!FILTER){
                                           updateVote = Vote;
                                           updateVote.yes++;
                                           updateVote.FilterUser = updateVote.FilterUser + "," + msg.getUserId();
                                           updateVote.CountUser = msg.getUsersCount();
                                           IndexUpdateVote = index;
                                           break;
                                   }
                               }
                               else {apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование не найдено!\n").execute();}
                           index++;
                           } 
                           if(IndexUpdateVote != -1){
                                SessionVoteList.set(IndexUpdateVote, updateVote);
                                if(updateVote.yes +  updateVote.no ==  updateVote.CountUser){                
                                                        String WIN = "";
                                                        if(updateVote.yes > updateVote.no){WIN = "Результат голосования - ДА!";}
                                                        if(updateVote.yes < updateVote.no){WIN = "Результат голосования - НЕТ!";}
                                                        if(updateVote.yes == updateVote.no){WIN = "Мнение разделилось! Ничья";}
                                                       apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование окончено! \n"
                                                       + "Проголосовало " + (updateVote.yes + updateVote.no) + " из " + updateVote.CountUser
                                                       + "("+ Math.round((((updateVote.yes + updateVote.no)*100)/updateVote.CountUser)) + "%)\n"
                                                       + WIN
                                                       ).execute(); System.out.println("Send msg Голосование окончено!");
                                               SessionVoteList.remove(IndexUpdateVote);
                                    } 
                                  else{apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голос засчитан! \n"
                                                   + "Проголосовало " + (updateVote.yes + updateVote.no) + " из " + updateVote.CountUser ).execute(); System.out.println("Send msg Голос засчитан!");}
                              }
                }
     }
     
        private static void no(Message msg, UserActor actor, VkApiClient apiClient) throws ApiException, ClientException{
                    if(msg.getBody().matches("/no") && msg.getChatId() != null){
                                VoteList updateVote = new VoteList();
                                int index = 0;
                                int IndexUpdateVote = -1;
                                boolean FILTER = false;
                                for(VoteList Vote : SessionVoteList){
                                   if(msg.getChatId() == Vote.chatID){
                                       for(String FilterUser : Vote.FilterUser.split(",")){
                                           if(Objects.equals(msg.getUserId().toString(), FilterUser)){FILTER = true;
                                               apiClient.messages().send(actor).chatId(msg.getChatId()).message("Ты уже голосовал!\n").execute();}}
                                       if(!FILTER){
                                               updateVote = Vote;
                                               updateVote.no++;
                                               updateVote.FilterUser = updateVote.FilterUser + "," + msg.getUserId();
                                               updateVote.CountUser = msg.getUsersCount();
                                               IndexUpdateVote = index;
                                               break;
                                       }
                                   }
                                   else {apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование не найдено!\n").execute();}
                               index++;
                               } 
                               if(IndexUpdateVote != -1){
                                    SessionVoteList.set(IndexUpdateVote, updateVote);
                                    if(updateVote.yes +  updateVote.no ==  updateVote.CountUser){                
                                                       String WIN = "";
                                                       if(updateVote.yes > updateVote.no){WIN = "Результат голосования - ДА!";}
                                                       if(updateVote.yes < updateVote.no){WIN = "Результат голосования - НЕТ!";}
                                                       if(updateVote.yes == updateVote.no){WIN = "Мнение разделилось! Ничья";}
                                                       apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голосование окончено! \n"
                                                       + "Проголосовало " + (updateVote.yes + updateVote.no) + " из " + updateVote.CountUser
                                                       + "("+ Math.round((((updateVote.yes + updateVote.no)*100)/updateVote.CountUser)) + "%)\n"
                                                       + WIN
                                                       ).execute(); System.out.println("Send msg Голосование окончено!");
                                               SessionVoteList.remove(IndexUpdateVote);
                                    } 
                                      else{apiClient.messages().send(actor).chatId(msg.getChatId()).message("Голос засчитан! \n"
                                                       + "Проголосовало " + (updateVote.yes + updateVote.no) + " из " + updateVote.CountUser ).execute(); System.out.println("Send msg Голос засчитан!");}
                                  }
                    }
         }
     
  
   private static Date addMinutesToDate(int minutes, Date beforeTime){
            long curTimeInMs = beforeTime.getTime();
            Date afterAddingMins = new Date(curTimeInMs + (minutes * 60000));
            return afterAddingMins;
    }  
}
