package vikrant;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.fazecast.jSerialComm.SerialPort;

public class send_aurdino <T> {
	
	private T t;
	static SerialPort chosenPort;
	
	public send_aurdino(T t) {
		  this.t = t;
		 }
	
	public void send() 
	{
		JFrame window = new JFrame();
		window.setTitle("Arduino LCD Clock");
		window.setSize(400, 175);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		JPanel topPanel = new JPanel();
		topPanel.add(portList);
		topPanel.add(connectButton);
		window.add(topPanel, BorderLayout.NORTH);
		
		SerialPort[] portNames = SerialPort.getCommPorts();
		
		for(int i = 0; i < portNames.length; i++)
			portList.addItem(portNames[i].getSystemPortName());
		
		
		connectButton.addActionListener(new ActionListener()
		{
			@Override public void actionPerformed(ActionEvent arg0) 
			{
				if(connectButton.getText().equals("Connect")) 
				{
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) 
					{
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
						
						// create a new thread for sending data to the arduino
						Thread thread = new Thread()
						{
							@Override public void run() 
							{
								// wait after connecting, so the bootloader can finish
								try {Thread.sleep(100); } catch(Exception e) {}

								// enter an infinite loop that sends text to the arduino
								PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
								output.print(t);
								output.flush();
								try {Thread.sleep(100); } catch(Exception e) {}
								output.close();
							}
						};
						thread.start();
					}
				} 
				else 
				{
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
				}
			}
		});
		window.setVisible(true);          //print frame-------------------------------------		
	}
}
