/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.clientchat;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import pl.krzyszczak.mikolaj.clientchat.connection.MainClientClass;
import pl.krzyszczak.mikolaj.clientchat.controller.Controller;
import pl.krzyszczak.mikolaj.clientchat.view.LoginView;
import pl.krzyszczak.mikolaj.clientchat.view.MessageView;
import pl.krzyszczak.mikolaj.clientchat.view.UserView;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

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
		MainClientClass client= new MainClientClass("localhost", 1200, eventQueue);
		Controller controller= new Controller(eventQueue, client);
		controller.work();
		

		
	
	}

}
