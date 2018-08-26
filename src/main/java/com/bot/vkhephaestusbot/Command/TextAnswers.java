package com.bot.vkhephaestusbot.Command;

import static com.bot.vkhephaestusbot.Main.ACTOR;
import static com.bot.vkhephaestusbot.Main.APICLIENT;
import static com.bot.vkhephaestusbot.Main.LOG;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
//Всякие разные команды
public class TextAnswers {
    private static String rdmYESNO() { int i = (int) (Math.random() * 2);if (i == 0) {return "Нет";}return "Да";}
	
    public static void TextAnswers(Message msg) throws ApiException, ClientException, IOException, ParserConfigurationException, SAXException{
            if(msg.getBody().matches("/хуй") && msg.getChatId() != null) {APICLIENT.messages().send(ACTOR).userId(msg.getUserId()).message("Шутник? тикай с городэ, тоби пизда").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  } 
            
            if(msg.getBody().matches("/test")) {
                if(msg.getChatId() == null){APICLIENT.messages().send(ACTOR).userId(msg.getUserId()).message("bot test, is done").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");} 
                else{APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("bot test, is done").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");}}
            
            if((msg.getBody().matches("/rdm(.*)")|| msg.getBody().matches("/r(.*)")) && msg.getChatId() != null) {APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message(rdmYESNO()).execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  } 
            
            if(msg.getBody().matches("/pron") && msg.getChatId() != null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("https://vk.com/doc501549239_472158068").openStream()); //обуз блокировки
            NodeList nodeList = doc.getElementsByTagName("loc");
            List<String> url = new ArrayList<>();
            for (int i=0;i<nodeList.getLength();++i) {
                     Element el = (Element) nodeList.item(i);
                     url.add(el.getTextContent());
                }
            String rdm = "Error";
            if(url.size() > 0){
            int index = new Random().nextInt(url.size());
            rdm = url.get(index);
            }
            APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message(rdm).execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  
            } 
            
            
            if(msg.getBody().matches("/hh") && msg.getChatId() != null) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL("http://hentaihaven.org/post-sitemap.xml").openStream());
            NodeList nodeList = doc.getElementsByTagName("loc");
            List<String> url = new ArrayList<>();
            for (int i=0;i<nodeList.getLength();++i) {
                     Element el = (Element) nodeList.item(i);
                     if(el.getTextContent() != "http://hentaihaven.org/")
                     url.add(el.getTextContent());
                }
            String rdm = "Error";
            if(url.size() > 0){
            int index = new Random().nextInt(url.size());
            rdm = url.get(index);
            }
            APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message(rdm).execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");     
            } 
          
            if(msg.getBody().matches("бот говно") && msg.getChatId() != null) {
                APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("тікай з городу, тобі пизда").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  
            }
           
            if(msg.getBody().matches("бот не работает") && msg.getChatId() != null) {
                APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("ярiк блять, бот потiк").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  
            }
          
            if(msg.getBody().matches("говно бот") && msg.getChatId() != null) {
                APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("тікай з городу, тобі пизда").execute(); System.out.println("[TextAnswers]Send message"); LOG.info("[TextAnswers]Send message");  
            }
    
    }          
        
    
    
}
