/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.clientchat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import pl.krzyszczak.mikolaj.clientchat.connection.MainClientClass;
import pl.krzyszczak.mikolaj.clientchat.view.LoginView;
import pl.krzyszczak.mikolaj.clientchat.view.MessageView;
import pl.krzyszczak.mikolaj.clientchat.view.UserView;
import pl.krzyszczak.mikolaj.clientchat.view.UsersViewTemporary;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * Klasa z funkcj¹ main dla aplikacji obs³uguj¹cej klienta
 * @author Miko³aj
 *
 */
public class ClientChat
{
	public static void main(String[] args)
	{
		//kolejka na eventy
		BlockingQueue<ApplicationEvent> eventQueue= new SynchronousQueue<ApplicationEvent>();
		//LoginView loginView=new LoginView(eventQueue);
		//MainClientClass client= new MainClientClass("localhost", 1200, eventQueue);
		MessageView messageView=new MessageView(eventQueue, new UserId(1));
		//UserView usersView= new UserView(eventQueue);
		
	
	}

}
