package project_calender;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class main {
	public connect connect = new connect();
	public JFrame frame = new JFrame("行事曆");
	public JMenuBar menubar = new JMenuBar();
	public JMenu option = new JMenu("選項");
	public JMenu about = new JMenu("關於");
	public JMenuItem[] i_option = new JMenuItem[4];
	public JMenuItem[] i_about = new JMenuItem[2];
	public MenuHandler mh = new MenuHandler();
	public ButtonHandler bh = new ButtonHandler();
	public JPanel p_setdate =new JPanel();
	public JPanel panel = new JPanel(new GridLayout(6,7));
	public JPanel[] p_date = new JPanel[42];
	public JPanel p_text = new JPanel(new GridLayout(1,7));
	public JPanel[] p_date_text = new JPanel[7];
	public JLabel[] date_text = new JLabel[7];
	public JButton[] b_setdate = new JButton[5];
	public JPanel setdate = new JPanel();
	public Date date = new Date();
	public Calendar calendar = Calendar.getInstance();
	public DateFormat df = DateFormat.getDateInstance();
	public JTextField month = new JTextField(Integer.toString(date.getMonth()+1));
	public JTextField year = new JTextField(Integer.toString(date.getYear()+1900));
	public JLabel[] day = new JLabel[42];
	public JLabel dash = new JLabel("/");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();
		
	}
	public main(){
		frame.setJMenuBar(menubar);
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c0 = new GridBagConstraints();
		c0.gridx = 0;
		c0.gridy = 0;
		c0.weightx = 7;
		c0.weighty = 0;
		c0.fill = GridBagConstraints.BOTH;
		c0.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 1;

		c1.weightx = 7;
		c1.weighty = 0;
		c1.fill = GridBagConstraints.BOTH;
		c1.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 2;
		c2.weightx = 7;
		c2.weighty = 0;
		c2.fill = GridBagConstraints.BOTH;
		c2.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 3;
		c3.weightx = 7;
		c3.weighty = 7;
		c3.fill = GridBagConstraints.BOTH;
		c3.anchor = GridBagConstraints.WEST;
		
		 
		menubar.add(option);
		i_option[0]= new JMenuItem("設定伺服器");
		i_option[1]= new JMenuItem("註冊");
		i_option[2]= new JMenuItem("登入");
		i_option[3]= new JMenuItem("登出");
		for(int i=0;i<4;i++){
			option.add(i_option[i]);
			i_option[i].addActionListener(mh);
		}
	
		
		
		
		menubar.add(about);
		i_about[0]= new JMenuItem("test");
		i_about[1]= new JMenuItem("test2");
		about.add(i_about[0]);
		about.add(i_about[1]);

		b_setdate[0]=new JButton("<<");
		p_setdate.add(b_setdate[0]);
		b_setdate[1]=new JButton("<");
		p_setdate.add(b_setdate[1]);
		b_setdate[2]=new JButton("切換到日期");
		p_setdate.add(b_setdate[2]); 
		b_setdate[3]=new JButton(">");
		p_setdate.add(b_setdate[3]);
		b_setdate[4]=new JButton(">>");
		p_setdate.add(b_setdate[4]);
		for(int i=0;i<5;i++){
			b_setdate[i].addActionListener(bh);
		}
		frame.add(p_setdate,c0);
		Dimension size = new Dimension(50,25);
		month.setPreferredSize(size);
		year.setPreferredSize(size);
		//month.setFont(f);
		setdate.add(month);
		setdate.add(dash);
		setdate.add(year);
		frame.add(setdate,c1);

		date_text[0] = new JLabel("Sun.");
		date_text[1] = new JLabel("Mon.");
		date_text[2] = new JLabel("Tue.");
		date_text[3] = new JLabel("Wed.");
		date_text[4] = new JLabel("Thu.");
		date_text[5] = new JLabel("Fri.");
		date_text[6] = new JLabel("Sat.");
		for(int i=0;i<7;i++){
			p_date_text[i]=new JPanel();
			p_text.add(p_date_text[i]);
			p_date_text[i].add(date_text[i]);
		}
		
		for(int i=0;i<42;i++){					//set p_data 外框
			p_date[i]=new JPanel();
			panel.add(p_date[i]);
			p_date[i].setBorder(BorderFactory.createLineBorder(new Color(200,200,255), 1)); 
			p_date[i].setBackground(new Color(240,240,255));
		}
		
		for(int i=0;i<42;i++){
			day[i]=new JLabel();
			p_date[i].add(day[i]);
		}
		
		setcalendar();
		frame.add(p_text,c2);
		frame.add(panel,c3);
		frame.setLocationRelativeTo(null);
		frame.setSize(800,800);
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void setcalendar(){
		try {
			calendar.setTime(df.parse(year.getText()+"/"+month.getText()+"/1"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//calendar.set(2015,05,28 );
		System.out.println(dayofweek());
		System.out.println(calendar.getActualMaximum(Calendar.DATE));
		for(int i=0;i<42;i++){
			day[i].setText("");
		}
		for(int i=dayofweek() , d=1;i<calendar.getActualMaximum(Calendar.DATE)+dayofweek();i++,d++){
			day[i].setText(String.valueOf(d));
		}
	}
	public int dayofweek(){
		int a=7;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			return 0;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
			return 1;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY)
			return 2;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
			return 3;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY)
			return 4;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			return 5;
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			return 6;
		else return a;
	}
	
	private class MenuHandler implements  ActionListener{
             
		 JFrame f_server = new JFrame("設定伺服器");
		 JButton s_button = new JButton("設定");
		 JLabel server = new JLabel("伺服器 domain  port :");
         JLabel sqluser = new JLabel("MySQL帳號 :");
         JLabel sqlpwd = new JLabel("MySQL密碼 :");
         JTextField domain = new JTextField();
         JTextField port = new JTextField("3306");
         JTextField s_user = new JTextField();
         JPasswordField s_pwd = new JPasswordField();
         
         JFrame f_register = new JFrame("註冊");
         JButton r_button =new JButton("送出");
         JLabel r_l_user = new JLabel("帳號 :");
         JLabel r_l_pwd = new JLabel("密碼 :");
         JLabel r_l_email = new JLabel("信箱 :");
         JTextField r_tf_user = new JTextField();
         JPasswordField r_pf_pwd = new JPasswordField();
         JTextField r_tf_email  = new JTextField();
         
         
		 public void actionPerformed(ActionEvent ae){   
			 
			 if(ae.getSource() == i_option[0]) {
                 f_server.setLayout(null);  	 
                 f_server.add(server);
                 f_server.add(sqluser);
                 f_server.add(sqlpwd);
                 f_server.add(domain);
                 f_server.add(port);
                 f_server.add(s_user);
                 f_server.add(s_pwd);
                 f_server.add(s_button);
                 server.setBounds(20, 20, 140, 25);
                 domain.setBounds(160, 20, 150, 25);
                 port.setBounds(320, 20, 50, 25);
                 sqluser.setBounds(20, 57, 100, 25);
                 s_user.setBounds(120, 57, 200, 25);
                 sqlpwd.setBounds(20, 90, 100, 25);
                 s_pwd.setBounds(120, 90, 200, 25);
                 s_button.setBounds(170, 125, 70, 25);
                 s_button.addActionListener(mh);
                 f_server.setSize(400, 200);
                 f_server.setResizable(false);
                 f_server.setLocationRelativeTo(frame);
                 f_server.setVisible(true);
			 }
             if(ae.getSource() == i_option[1]){ 
            	 f_register.setLayout(null);
            	 f_register.add(r_button);
            	 f_register.add(r_l_user);
            	 f_register.add(r_l_pwd);
            	 f_register.add(r_l_email);
            	 f_register.add(r_tf_user);
            	 f_register.add(r_pf_pwd);
            	 f_register.add( r_tf_email);
            	 r_l_user.setBounds(40, 20, 90, 25);
            	 r_l_pwd.setBounds(40, 55, 90, 25);
            	 r_l_email.setBounds(40, 90, 90, 25);
            	 r_tf_user.setBounds(130, 20, 200, 25);
            	 r_pf_pwd.setBounds(130, 55, 200, 25);
            	 r_tf_email.setBounds(130, 90, 200, 25);
            	 r_button.setBounds(170, 125, 70, 25);
            	 
            	 r_button.addActionListener(mh);
            	 f_register.setSize(400, 200);
                 f_register.setResizable(false);
                 f_register.setLocationRelativeTo(frame);
                 f_register.setVisible(true);
             }
             if(ae.getSource() == i_option[2]){
                             System.exit(0);
             }
             if(ae.getSource() == i_option[3]){
             
             }
             if(ae.getSource() == s_button){
            	 connect.connect(domain.getText(), port.getText(), s_user.getText(), s_pwd.getText());
            	 f_server.setVisible(false);
             }
             if(ae.getSource() == r_button){
            	 connect.register(r_tf_user.getText(),r_pf_pwd.getText(),r_tf_email.getText());
             }
                    
                     
		 }
     }
	private class ButtonHandler implements  ActionListener{
		
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource() == b_setdate[0]){
				
				year.setText( String.valueOf(Integer.parseInt(year.getText())-1));
				setcalendar();
			}
			if(ae.getSource() == b_setdate[1]){
				if(month.getText().equals("1")){
					month.setText("12");
					year.setText( String.valueOf(Integer.parseInt(year.getText())-1));
					setcalendar();
				}
				else if(Integer.parseInt(month.getText())<1 || Integer.parseInt(month.getText())>12){
					JOptionPane.showMessageDialog(null, "不存在的月份", "錯誤", JOptionPane.ERROR_MESSAGE );
				}
				else{
					month.setText( String.valueOf(Integer.parseInt(month.getText())-1));
					setcalendar();
				}
			}
			if(ae.getSource() == b_setdate[2]){
				if(Integer.parseInt(month.getText())<1 || Integer.parseInt(month.getText())>12){
					JOptionPane.showMessageDialog(null, "不存在的月份", "錯誤", JOptionPane.ERROR_MESSAGE );
				}
				else{
					setcalendar();
				}
			}
			if(ae.getSource() == b_setdate[3]){
				if(month.getText().equals("12")){
					month.setText("1");
					year.setText( String.valueOf(Integer.parseInt(year.getText())+1));
					setcalendar();
				}
				else if(Integer.parseInt(month.getText())<1 || Integer.parseInt(month.getText())>12){
					JOptionPane.showMessageDialog(null, "不存在的月份", "錯誤", JOptionPane.ERROR_MESSAGE );
				}
				else{
					month.setText( String.valueOf(Integer.parseInt(month.getText())+1));
					setcalendar();
				}
			}
			if(ae.getSource() == b_setdate[4]){
				year.setText( String.valueOf(Integer.parseInt(year.getText())+1));
				setcalendar();
			}
		}
	}

}