<h1><a id="VKBOTHephaestu_0"></a>VKBOT-Hephaestu</h1>
<p>Bot for VK. Basis for the bot. It's easy to set up a new command<br>
Bot uses an account, not a group.<br>
A simple bot, wrote from boredom
</p>
<h4><a id="List_of_commands_already_included_in_the_bot_7"></a>List of commands already included in the bot:<br>
/help - List of Commands<br>
/fake - Anti-deadline - broken files<br>
/vote "< QUESTION >" < TIME TO VOTE > - Start voting<br>
/rdm - Random answer (Example of a shortened command: '/r Going to the store?')<br>
</h4>
You yourself can easily add new commands
<h4><a id="Dependencies_11"></a>Dependencies</h4>
<p>IDE NetBans 8.2<br>
The project uses:</p>
<ul>
<li>Java JDK version 1.8 or higher</li>
<li>Maven</li>
<li>VK Java SDK 0.5.12 and all its dependencies [<a href="https://vk.com/dev/Java_SDK">https://vk.com/dev/Java_SDK</a>]</li>
</ul>
<h4><a id="Project_structure_18"></a>Project structure</h4>
<p>Bot uses the user’s account, not the api group</p>
<p>Main.java - The title explains everything<br>
LongPoll.java - There are new events vk checked there. New event can be processed in ResponseProcessing<br>
ResponseProcessing.java  - Here new commands are added. There is also a set of commands.<br>
TimeEvent.java - Started every minute. Serves for handling commands associated with a deferred response.</p>
<p>(NEW EVENT) LongPoll.java  --&gt; (Processing this event) ResponseProcessing.java</p>
<p>To create a new command:</p>
<ol>
<li>Create a new Java class (Recommended in the package “com.bot.vkhephaestusbot.Command”).</li>
<li>Сreate a public static method.</li>
<li>Your method should accept “Message”</li>
</ol>
<pre><code>Example:
public static void TextAnswers(Message msg){}
</code></pre>
<p>4.Process “msg” in your class.<br>
Use the “UserActor” and the “VkApiClient” from the VK JAVA SDK (<a href="https://vk.com/dev/Java_SDK">https://vk.com/dev/Java_SDK</a>)<br>
Use global UserActor actor and VkApiClient apiClient</p>
<pre><code>Example:
public class Fils {
    public static void Fils(Message msg) throws ApiException, ClientException{
                if(msg.getBody().matches("/fil") && msg.getChatId() != null) { //Check for match of the command
                Main.APICLIENT.messages().send(ACTOR).chatId(msg.getChatId()).message("IS COMANDS").attachment().execute();
                System.out.println("[Fils]Send message"); LOG.info("[Fils]Send message");}
    }  
}
</code></pre>
<p>
5. Call your function in ResponseProcessing.java
</p>
<p>LICENSE WTFPL</p>
