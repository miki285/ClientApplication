package pl.krzyszczak.mikolaj.clientchat.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;

/**
 * G��wna klasa aplikacji klienta, s�u�y do komunikacji z serwerem, wysy�aniem
 * danych event�w oraz odbierania od serwera odpowiednich makiet
 * 
 * @autor Krzyszczak Miko�aj
 */
public class MainClientClass
{
	/** Adres serwera */
	private String serverAddress;
	/** numer portu serwera */
	private int port;
	/** Socket klienta */
	private Socket socket;
	/** UserID danego klienta */
	private UserId userId;

	/** Strumien wejsciowy */
	private ObjectInputStream inStream;
	/** Strumien wyjsciowy */
	private ObjectOutputStream outStream;

	/** Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;

	/** Wy�wietlanie czasu */
	private SimpleDateFormat simpleDateFormat;

	/**
	 * konstruktor klasy clienta
	 */
	public MainClientClass(final String serverAddress, final int port,
			final BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.serverAddress = serverAddress;
		this.port = port;
		this.eventQueue = eventQueue;
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

		this.run();
	}

	/** Metoda w��czaj�ca klienta */

	private void run()
	{
		display("Klient pr�buje nawi�za� po��czenie z serwerem");

		try
		{
			this.socket = new Socket(this.serverAddress, this.port);
			this.inStream = new ObjectInputStream(socket.getInputStream());
			this.outStream = new ObjectOutputStream(socket.getOutputStream());

			this.sendEvent();
			ServerListener serverListener = new ServerListener();
			serverListener.start();

			this.sendEvent();

		} catch (IOException e)
		{
			System.exit(1);
		}

	}

	/**
	 * Metoda ustawiaj�ca UserId nadane przez serwer
	 */
	public void setUserId(UserId userId)
	{
		this.userId = userId;
	}

	/**
	 * Metoda wysy�aj�ca wiadomo�� o evencie do serwera
	 */
	public void sendEvent()
	{
		while (true)
		{
			try
			{
				ApplicationEvent applicationEvent = eventQueue.take();

				try
				{
					outStream.writeObject(applicationEvent);
				} catch (IOException e)
				{
					System.exit(1);
				}
			} catch (InterruptedException e)
			{

			}
		}

	}

	/**
	 * Metoda do wy�wietlania wiadomosci razem z dat�
	 */
	private void display(String msg)
	{
		String time = simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
	}

	/**
	 * Klasa nas�uchuj�ca po�acze� od serwera TODO obs�ug� widoki na podstawie
	 * otrzymanej makiety
	 */
	public class ServerListener extends Thread
	{

		public void run()
		{
			display("Klient nas�uchuje wiadomo�ci");
			while (true)
			{
				try
				{
					ApplicationEvent applicationEvent = (ApplicationEvent) inStream
							.readObject();
					eventQueue.add(applicationEvent);
				} catch (IOException | ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
}
