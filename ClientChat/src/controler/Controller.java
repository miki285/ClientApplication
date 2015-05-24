package controler;

import helpfull.UserId;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;








import view.ChatView;
import view.LoginView;
import view.UsersView;
import connection.MainClientClass;
import appEvent.ApplicationEvent;


public class Controller {
	/*Kolejka blokuj¹ca eventy*/
	private BlockingQueue<ApplicationEvent> eventQueue;
	/*referencja do klienta*/
	private MainClientClass client;
	/*referencja do okna logowania*/
	private LoginView loginView;
	/*referencja do okna z kontaktami*/
	private UsersView usersView;
	/*Hashmapa referencji do okien rozmów z pojedynczymi u¿ytkownikami*/
	private HashMap<UserId, ChatView> chatView;
	/*Mapa strategii obs³ugi zdarzeñ*/
	private Map<Class<? extends ApplicationEvent>, ApplicationEventStrategy> strategyMap;
	

		
	/*
	 * Konstruktor kontrolera
	 */
	
	public Controller( final MainClientClass client, 
	
	
	
	
		abstract class ApplicationEventStrategy{
			abstract void execute(final ApplicationEvent applicationEvent);
		}
}
