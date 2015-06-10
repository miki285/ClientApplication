/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;

/**
 * Klasa odpowiedzialma za wyœwietlanie listy userów u danego u¿ytkownika
 * 
 * @author Miko³aj
 *
 */
public class UserView
{

	/** Referencja na kolejkê do której dodawane s¹ eventy */
	BlockingQueue<ApplicationEvent> eventQueue;
	/** g³ówna ramka */
	private JFrame frame;

	/** lista zaprzyjaznionych uzytkownikow */
	private JList<samochod> userList;
	
	/** lista zaprzyjaznionych uzytkownikow */
	private JList<samochod> usersFriendList;
	
	/**Lista elementów do wyœwietlenia*/
	private final DefaultListModel<samochod> listModel;
	
	/**Przycisk dodania znajomego */
	private JButton addFriendButton;
	
	/**Przycisk wys³ania wiadomoœci */
	private JButton sendMessageButton;
	
	/** Etykieta do opisuj¹ca pole JList zawieraj¹cych wszystkich u¿ytkowników na serwerze */
	private JLabel allUsersLabel;
	
	/** Etykieta do opisuj¹ca pole JList zawierajacych znajomych danego uzytkownika */
	private JLabel usersFriendsLabel;
	
	/** Panel do wszystkich u¿ytkowników */
	private JPanel usersPanel;
	/** Panel do logowania */
	private JPanel usersFriendPanel;
	/**Jscrolpannel do wszysktich uzytkownikow*/
	private JScrollPane usersPanelScroll;
	
	public UserView(BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		
		//Frame
		frame=new JFrame("Okno kontaktow");
		frame.setSize(330, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Label
		usersFriendsLabel=new JLabel("Znajomi: ");
		usersFriendsLabel.setBounds(15, 15, 100, 50);
		
		allUsersLabel=new JLabel("Wszyscy uzytkownicy :");
		allUsersLabel.setBounds(15, 365, 150, 50);
		
		
		//Buttons
		
		sendMessageButton=new JButton("Wiadomosc");
		sendMessageButton.setBounds(194, 355, 120, 25);
		
		addFriendButton= new JButton("Dodaj");
		addFriendButton.setBounds(234, 705, 80, 25);
		
		/** @ TODO obs³ugê przyciwsków */
		
		//ListModel
		listModel = new DefaultListModel<>();
		listModel.addElement(new samochod(123, "maluch"));
		listModel.addElement(new samochod(1233, "maluczh"));
		listModel.addElement(new samochod(1523, "zxmaluch"));
		listModel.addElement(new samochod(1623, "casmaluch"));
		listModel.addElement(new samochod(1123, "fffmaluch"));
		listModel.addElement(new samochod(3123, "gggmaluch"));
		listModel.addElement(new samochod(1423, "oooomaluch"));
		listModel.addElement(new samochod(123123, "aasdmaluch"));
		listModel.addElement(new samochod(666,"Diabe³"));
		
		
		//User List
		userList = new JList<samochod>(listModel);
		userList.setBounds(15, 400, 300, 300);
		userList.setCellRenderer(new ownRenderer());
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setLayoutOrientation(JList.VERTICAL);
		
		//Scroll
		usersPanelScroll=new JScrollPane(userList);
		usersPanelScroll.setBounds(15, 400, 300, 300);
		
		//UserFriendList
		usersFriendList = new JList<samochod>(listModel);
		usersFriendList.setBounds(15,50, 300, 300);
		usersFriendList.setCellRenderer(new ownRenderer());
		usersFriendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersFriendList.setLayoutOrientation(JList.VERTICAL);
		
		/*userList.addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if(!e.getValueIsAdjusting())
				{
					samochod seleList= userList.getSelectedValue();
					listModel.addElement(seleList);
					resd();
					System.out.println(seleList);
					
				}
				
			}
		});*/
		MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		         if (e.getClickCount() == 2) {
		        	 samochod seleList= userList.getSelectedValue();
						listModel.addElement(seleList);
						resd();
						System.out.println(seleList);
		          }
		     }
		 };
		userList.addMouseListener(mouseListener);
		
		//frame.add(userList);
		frame.add(usersFriendsLabel);
		frame.add(allUsersLabel);
		
		frame.add(sendMessageButton);
		frame.add(addFriendButton);
		
		frame.add(usersFriendList);
		frame.add(usersPanelScroll);
		frame.setResizable(false);
		frame.setVisible(true);
		
	
	}
	
	private void resd()
	{
		
		JList newList= new JList<samochod>(listModel);
		/*frame.remove(userList);
		frame.add(newList);
		frame.add(new JScrollPane(newList));*/
	//	frame.add(userList);
		frame.add(usersFriendList);
		usersPanelScroll= new JScrollPane(newList);
		usersPanelScroll.setBounds(15, 400, 300, 300);
		frame.add(usersPanelScroll);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
	}
	
	public class ownRenderer extends JLabel implements ListCellRenderer<samochod>
	{
		protected Border focusBorder;
		/* (non-Javadoc)
		 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */

		public ownRenderer()
		{
			focusBorder = new EmptyBorder(1, 1, 1, 1);
			setOpaque(true);
		}
		@Override
		public Component getListCellRendererComponent(JList<? extends samochod> list, samochod value, int index,boolean isSelected, boolean cellHasFocus)
		{
			setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
			setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
			
			
			setFont(list.getFont());
			
			setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : focusBorder);
			
			setText(value.getNazwa());
			return this;
		}
		
	}
	
	class samochod
	{
	

		private int tablice;
		private String	nazwa;
		
		public samochod (int tablice, String nazwa)
		{
			this.tablice=tablice;
			this.nazwa=nazwa;
		}
		
		/**
		 * @return the nazwa
		 */
		public String getNazwa()
		{
			return nazwa;
		}

		

		/**
		 * @return the tablice
		 */
		public int getTablice()
		{
			return tablice;
		}

	
		
	}

}
