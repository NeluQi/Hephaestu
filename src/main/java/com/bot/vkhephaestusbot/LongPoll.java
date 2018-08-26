package com.bot.vkhephaestusbot;

import com.bot.vkhephaestusbot.Auxiliary.VoteList;
import static com.bot.vkhephaestusbot.Main.LOG;
import static com.bot.vkhephaestusbot.Main.stopBOT;
import static com.bot.vkhephaestusbot.ResponseProcessing.ResponseProcessing;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.LongpollParams;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.responses.GetLongPollHistoryResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class LongPoll implements Runnable {

    private final UserActor actor;
    private final VkApiClient apiClient;
    public static List<VoteList> SessionVoteList = new ArrayList<>(); //Активные голосования 

    public LongPoll(VkApiClient apiClient, UserActor actor) {
        this.actor = actor;
        this.apiClient = apiClient;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (!stopBOT) { // fault tolerance
            try {
                LongpollParams LongPollServer = apiClient.messages().getLongPollServer(actor).needPts(true).execute();
                int ts = LongPollServer.getTs();
                int pts = LongPollServer.getPts();
                LOG.info("Bot running...");
                System.out.println("Bot running, is good...");
                System.out.println(">>>");
                //Проверяем постоянно нет ли новых событий 
                //Не совсем правильно сделано, но так работает и хуй с ним. Может потом переделаю
                while (!stopBOT) {
                    GetLongPollHistoryResponse LongPollHistory = apiClient.messages().getLongPollHistory(actor).ts(ts).pts(pts).execute();
                    Thread.sleep(750);
                    if (LongPollHistory.getMessages().getCount() > 0) { //Новое событие 
                        LOG.info("New event: ");
                        System.out.println("New event: " + LongPollHistory.getMessages().getMessages().get(0).toString());
                        LOG.info(LongPollHistory.getMessages().getMessages().get(0).toString());
                        ResponseProcessing((Message) LongPollHistory.getMessages().getMessages().get(0));
                    }
                    pts = LongPollHistory.getNewPts(); // обновляем pts
                }
            } catch (ApiException | ClientException | IOException | InterruptedException | ParseException | ParserConfigurationException | SAXException ex) {
                //Если ошибка, перезапускаем поток
                //Мало ли какое говно может вылезти
                Logger.getLogger(LongPoll.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
