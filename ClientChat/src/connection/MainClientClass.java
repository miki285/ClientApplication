package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import appEvent.ApplicationEvent;
/*
 * G��wna klasa klienta
 * @autor Krzyszczak Miko�aj
 */
public class MainClientClass {

	/* Strumien wejsciowy */
	private ObjectInputStream inStream;
	/*Strumien wyjsciowy*/
	private ObjectOutputStream outStream; 
	/* Kolejka blokuj�ca eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/*Adres serwera*/
	private String serverAddress;
	/*numer portu serwera*/
	private int port;
	/*Socket klienta*/
	private Socket socket;
	//Wy�wietlanie czasu
	private SimpleDateFormat simpleDateFormat;
	
	
	/*
	 * konstruktor klasy clienta
	 */
	public MainClientClass(String serverAddress, int port, BlockingQueue<ApplicationEvent> eventQueue){
		this.serverAddress=serverAddress;
		this.port=port;
		this.eventQueue=eventQueue;
		this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
		
		this.run();
	}
	
	
	/*Metoda w��czaj�ca klienta*/
	
	private void run(){
		display("Klient pr�buje nawi�za� po��czenie z serwerem");
		
		try{
			this.socket=new Socket(this.serverAddress, this.port);
			this.inStream=new ObjectInputStream(socket.getInputStream());
			this.outStream=new ObjectOutputStream(socket.getOutputStream());
			
			ServerListener serverListener= new ServerListener();
			serverListener.start();
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Metoda wysy�aj�ca wiadomo�� o evencie do serwera
	 * 
	 */
	public void sendEvent(ApplicationEvent applicationEvent){
		try{
			outStream.writeObject(applicationEvent);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Metoda do wy�wietlania wiadomosci razem z dat�
	 */
	private void display(String msg){
		String time=simpleDateFormat.format(new Date()) + " " + msg;
		System.out.println(time);
	}
	
	/*
	 * DO POPRAWY!!!!!!!!!!!!!!!!!!!
	 * Klasa nas�uchuj�ca po�acze� od serwera
	 */
	public class ServerListener extends Thread {
		
		public void run(){
			display("Klient nas�uchuje wiadomo�ci");
			while(true)
			{
				try
				{
					ApplicationEvent applicationEvent= (ApplicationEvent) inStream.readObject();
					eventQueue.add(applicationEvent);
				} 
				catch( IOException | ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		}

	}
}
