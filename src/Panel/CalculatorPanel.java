package Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorPanel extends JPanel implements ActionListener {

	JPanel container = new JPanel(new FlowLayout());
	

	JTextField result;
	JPanel mainPane = new JPanel();



	
	Dimension MainPane = new Dimension(450,480);

	
	JButton b[] = new JButton[16];
	String s[] = {"0","1","2","3","4","5","6","7","8","9","+","-","/","*","=","C"};
	JPanel buttons = new JPanel();
	
	String screen="";
	String monitor1="",monitor2="",OperationOnScreen=""; 
	
	
	//JLabel screen = new JLabel();
	
	boolean CommandEmpty=true,switcher=true;
	double R=Integer.MIN_VALUE,L=Integer.MIN_VALUE;
	
//	Dimension dimensionTop = new Dimension(450, 90);
	
	Dimension dimensionTextField = new Dimension(450, 90);
	Font fontTextField = new Font("Arial", Font.BOLD, 47);
	
	Dimension dimensionMainPane = new Dimension(450,450);
	
	
	public CalculatorPanel() {
		
		super();
		setBounds(100, 100, 480, 800);
		setOpaque(false);
	
		
//		
//		top.setPreferredSize(dimensionTop);
//	
		
		result = new JTextField();
		result.setEditable(false);
		result.setBackground(Color.PINK);
		result.setPreferredSize(dimensionTextField);
		result.setFont(fontTextField);
		
		mainPane.setPreferredSize(dimensionMainPane);
		buttons.setPreferredSize(dimensionMainPane);
		
		buttons.setLayout(new GridLayout(4,4,15,15));
		for (int i=0;i<16;i++)
		{
			b[i] = new JButton(s[i]);
			b[i].addActionListener(this);
			buttons.add(b[i]);
		}
		
//		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	

		mainPane.setPreferredSize(MainPane);
		
		mainPane.add(buttons);
		
		buttons.setOpaque(false);
		container.setOpaque(false);
	
		mainPane.setOpaque(false);
	
	
		container.add(result);
		container.add(mainPane);

	
		add(container);
		
	
		
		setVisible(true);
		
		}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
			for (int i=0; i<=9; i++)//Numbers
			{
				if(event.getSource()==b[i])
				{					
					screen+=i;
					result.setText("");
					result.setText(screen);	
				}
			}
				
			for (int i=10; i<=14; i++)//Commands
			{
				if(event.getSource()==b[i])
				{
					if(result.getText().lastIndexOf(OperationOnScreen)!=-1)//prevent exception
						result.setText(result.getText().substring(0,result.getText().lastIndexOf(OperationOnScreen))+s[i]);
					else
						result.setText(result.getText()+s[i]);
					OperationOnScreen=s[i];	
						
					if(switcher)
					     {monitor1=s[i];switcher=false;}
					else {monitor2=s[i];switcher=true;}
					
					if (monitor1!=monitor2 && monitor2!="")
						{
						if(switcher) //execute older,send sign newer
					   	 	 {Calc(event,monitor1.charAt(0),monitor2); }
						else {Calc(event,monitor2.charAt(0),monitor1); }
					    }
					if(s[i]!="=") //calc returns 0
						Calc(event,s[i].charAt(0),s[i]);
				}					
			}
			
			if(event.getSource()==b[15]) //Clear
			{
				screen=""; monitor1=""; monitor2=""; 
				switcher=true; CommandEmpty=true;
				result.setText("");
			}
		
	}
		
		public void Calc(ActionEvent event,char OpType,String Operator)
		{		if (Operator=="=")
					Operator="";
					
				if(CommandEmpty && screen=="")
				{
					return;
				}
					
				else if(CommandEmpty && screen!="")
				{
					R=Integer.parseInt(screen);
					result.setText(screen+Operator);
					screen="";
					CommandEmpty=false;
				}
				else if(!CommandEmpty && screen!="")
				{
					L=Integer.parseInt(screen);
					R=Operations(R,L,OpType);//calculate
					screen="";
					result.setText("");
					result.setText(R+Operator);
				}	
		}
	
		
		public static double  Operations(double R, double L, char op)
		{	
			switch (op)
			{
				case '+':
					return R+L;
				case '-':
					return R-L;
				case '*':
					return R*L;
				case '/':
					return R/L;											
			}
			return 0;
		}
		
		public static void main ( String [] args)
		{
			CalculatorPanel c = new CalculatorPanel();
		}
		

}
