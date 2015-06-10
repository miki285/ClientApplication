package pl.krzyszczak.mikolaj.clientchat.view;

import java.awt.Font;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import org.omg.CORBA.INITIALIZE;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/** Klasa odpowiedzialma za wy�wietlanie listy user�w u danego u�ytkownika */
public class UsersViewTemporary
{
	/**Referencja na kolejk� do kt�rej dodawane s� eventy*/
	BlockingQueue<ApplicationEvent> eventQueue;
	/** g��wna ramka*/
	private JFrame frame;
	
	/**Obszar gdzie s� wy�wietlani wszyscy u�ytkownicy*/
	private JTextArea allUsers;
	/**Scroll do obszaru gdzie wyswietlani sa wszyscy uzytkownicy */
	private JScrollPane allUsersJScrollPane;
	
	/**Obszar JList znajomych danego u�ytkownika */
	private JTextArea usersFriend;
	/**Scroll do obszaru gdzie wy�wietlani s� znajomi danego u�ytkownika */
	private JScrollPane usersFriendJScrollPane;
	
	/**Konstruktor inicjalizuj�cy i wy�wietlaj�cy ramk� */
	public UsersViewTemporary(final BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue=eventQueue;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * metoda inicjalizuj�ca okno do wy�wietlenia
	 */
	private void initialize()
	{
		frame=new JFrame("Okno kontaktow");
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**Inicjowanie obszaru listy wszystkich u�ytkownikow */
		allUsers=new JTextArea();
		allUsers.setBounds(15, 400, 350, 300);
		allUsers.setEditable(false);
		frame.getContentPane().add(allUsers);
		allUsersJScrollPane=new JScrollPane(allUsers);
		allUsersJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		allUsersJScrollPane.setBounds(15, 300, 350, 300);
		frame.getContentPane().add(allUsersJScrollPane);
		
		
		
		
	}
	
}
