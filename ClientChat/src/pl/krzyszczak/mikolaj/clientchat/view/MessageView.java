/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.view;

import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * @author Miko�aj
 *
 */
public class MessageView
{
	/** referancja na kolejk� na eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;

	/** Ramka aplikacji */
	private JFrame frame;

	/** Obszar gdzie wy�wietlana jest rozmowa pomiedzy u�ytkownikami */
	private JTextArea usersConversation;

	/** Obszar gdzie u�ytkownik wpisuje swoj� wiadomo�� */
	private JTextArea usersTextMessagefield;

	/** Przycisk sygnalizuj�cy wys�anie wiadomo�ci */
	private JButton sendButton;

	/** Scrollery poszczeg�lnych obszar�w tekstowych */
	private JScrollPane usersConversationScroll;
	private JScrollPane usersTextMessageScroll;

	/** Charakterystyczny numer ID uzytkownika z ktorym piszemy */
	private UserId friendsUserId;

	public MessageView(BlockingQueue<ApplicationEvent> eventQueue, UserId friendsUserId)
	{
		this.eventQueue = eventQueue;
		this.friendsUserId= friendsUserId;
		System.out.println(this.friendsUserId.getId());
		initialize();
		frame.setVisible(true);

	}
	/** Metoda inicjalizuj�ca wszystkie zmienne MessageView */
	private void initialize()
	{
		//Frame
		frame=new JFrame("Rozmowa z uzytkownikiem o ID :" + friendsUserId.getId());
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		usersConversation= new JTextArea();
		//usersConversation.setBounds(15, 15, 500, 250);
		usersConversation.setEditable(false);
		
		usersConversationScroll= new JScrollPane(usersConversation);
		usersConversationScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersConversationScroll.setBounds(15, 15, 550, 270);
		
		usersTextMessagefield= new JTextArea();
		usersTextMessagefield.setEditable(true);
		
		usersTextMessageScroll= new JScrollPane(usersTextMessagefield);
		usersTextMessageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersTextMessageScroll.setBounds(15, 290, 550, 100);
		
		sendButton= new JButton("Wy�lij");
		sendButton.setBounds(465, 400, 100, 30);
		
		frame.add(usersConversationScroll);	
		frame.add(usersTextMessageScroll);
		frame.add(sendButton);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
