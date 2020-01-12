import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Login extends JFrame {
	private SiteInfo siteInfo;
	private JButton loginBtn;
	private JButton exitBtn;
	private JPasswordField pwT;
	String pw = "admin";

   
	public Login() {
   
		setTitle("����� �α��� by.yewon");
      
		LoginP();
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
      
   }
   
	private void LoginP() {
		JPanel p = new JPanel();
		JPanel Text = new JPanel();
		JPanel Btn = new JPanel();
		
		p.setLayout(new GridLayout(2,1));
		Btn.setLayout(new FlowLayout(FlowLayout.CENTER));
      
		p.setBorder(new TitledBorder(new LineBorder(Color.black,1),"�ȳ��ϼ���?"));
      
		JLabel pwL = new JLabel("��й�ȣ ");
		pwT = new JPasswordField(10);
		loginBtn = new JButton("�α���");
		exitBtn= new JButton("����");
		
		Text.add(pwL);
		Text.add(pwT);
      
		Btn.add(loginBtn);   
		Btn.add(exitBtn);   
          
		p.add(Text);
		p.add(Btn);
		this.add(p);
		
		// �α��� ��ư
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				check();
            
			}
         
		});
		
		
		// �����ư
		exitBtn.addActionListener( new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
               int bye = JOptionPane.showConfirmDialog(Login.this, "������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
               if (bye == JOptionPane.YES_OPTION) {
                  System.exit(0);
               }
               else return;               
            }
         });
   
	}	
   
	public void check() {
		if(pwT.getText().equals(pw)) {
			dispose();
		}
		else {
			pwT.setText("");
		}
      
	}
	public static void main(String[] args) {
		new Login();
	}
   
}
