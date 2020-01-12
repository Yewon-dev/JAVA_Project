import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class SiteInfoManagerApp extends JFrame{
	//main �޼��带 ���� 
	//SiteInfoManager ��ü ����

	ArrayList<HashMap<String,String>> siteInfoList = new ArrayList<HashMap<String,String>>();
	

	
	//TablePanel
	private JPanel tableP = new JPanel();
	
	//createSiteInfo
	private JPanel createP, basicInfoP, addInfoP, btnP, searchP, arrayP;
	private JTextField nameT, addT, idT, pwT;
	private JComboBox<String> group_a1, group_b1;
	private JButton btnN, btnI;
	private JTextArea memoTA;
	
	private String[] group_a = {"�з�", "�Ϲ�", "�б�", "����", "����"};
	private String[] group_b = {"������", "��", "�١�", "�١١�", "�١١١�", "�١١١١�"};
	private String[] group_c = {"��ü", "�з�", "��ȣ��", "����Ʈ �̸�", "����Ʈ �ּ�"};
	
	//TabPanel
	private JTextField filterT;
	private JComboBox<String> group_a2, group_c1, group_c2;
	private JButton arrayBtn, basicBtn;
	
	//������������ Panel
	private JCheckBox check;
	private JButton deleteBtn;
	private JLabel siteNumber;
	private JTextField idTF, pwTF;
	
	
	protected JTable table;
	
	protected DefaultTableModel dtm;
	
	SiteInfoManagerApp() {
		super("�������� ���α׷� :) by.yewon");
		

		Menu(); //����� �޴�
		SiteInfoPanel(); // [�Է� / ����] �г�
		
		JTabbedPane pane = createTabbedPane(); // Tab
		this.add(pane, BorderLayout.CENTER);
		
		SiteNumPanel(); // ����Ʈ ���� �г�
		
		
	    setSize(900, 700);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//�޴���
	private void Menu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		//���� �޴�
		JMenu mF = new JMenu("����(F)");
		JMenu mM = new JMenu("����(M)");
		JMenu mS = new JMenu("����(S)");
		
		menubar.add(mF);
		menubar.add(mM);
		menubar.add(mS);
		
		//���� �޴� - ����
		JMenuItem fI = new JMenuItem("���� ���Ͽ��� ��������(I)");
		JMenuItem fE = new JMenuItem("���� ���Ͽ��� ��������(E)");
		JMenuItem fS = new JMenuItem("����(S)");
		JMenuItem fO = new JMenuItem("�α׾ƿ�(O)");
		JMenuItem fX = new JMenuItem("����(X)");
		
		//fI.addActionListener(input);
		fI.setEnabled(false);
		//fE.addActionListener(output);
		fE.setEnabled(false);
		fS.addActionListener(save);
		fO.addActionListener(logout);
		fX.addActionListener(exit);
		
		mF.add(fI);
		mF.add(fE);
		mF.add(fS);
		mF.addSeparator();
		mF.add(fO);
		mF.add(fX);
		
		mF.setMnemonic('F');
		mM.setMnemonic('M');
		mS.setMnemonic('S');
		fI.setMnemonic('I');
		fE.setMnemonic('E');
		fS.setMnemonic('S');
		fO.setMnemonic('O');
		fX.setMnemonic('X');
		
		//���� �޴� - ����
		JMenuItem mU = new JMenuItem("�����(U)");
		JMenuItem mC = new JMenuItem("����Ʈ �з�(C)");
		
		//mU.addActionListener(user);
		mU.setEnabled(false);
		mC.addActionListener(classify);
		
		mM.add(mU);
		mM.add(mC);
		
		mU.setMnemonic('U');
		mC.setMnemonic('C');
		
		//���� �޴� - ����
		JCheckBoxMenuItem sL = new JCheckBoxMenuItem("�ڵ� �α���(L)");
		JCheckBoxMenuItem sV = new JCheckBoxMenuItem("�������� ���� ���� ����ϱ� (V)");
		
		sL.addItemListener(autologin);
		sV.addItemListener(remember);
		
		mS.add(sL);
		mS.add(sV);	
	
		sL.setMnemonic('L');
		sV.setMnemonic('V');
		
	}
	private JPanel SiteInfoPanel() {
		
		//���� �Է� �г�
		createP = new JPanel(new BorderLayout());
		createP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"�Է� / ����"));
		
		//�⺻ ����
		basicInfoP = new JPanel();
		basicInfoP.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "�⺻ ����"));
	    basicInfoP.setLayout(new GridLayout(4,1));
	    	
	    //�⺻���� - ����Ʈ��
		JPanel nameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameP.add(new JLabel("����Ʈ��"));
		nameT = new JTextField(14);
		nameP.add(nameT);
		
		//�⺻���� - ����Ʈ �ּ�
		JPanel addP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    addP.add(new JLabel("�ּ�(URL)  http://", JLabel.LEFT));
	    addT = new JTextField(10);
	    addP.add(addT);

	    //�⺻���� - ���̵�
	    JPanel idP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    idP.add(new JLabel("��  ��  ��", JLabel.LEFT));
	    idT = new JTextField(10);
	    idP.add(idT);
	     
	    //�⺻���� - ��й�ȣ
	    JPanel pwP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pwP.add(new JLabel("��й�ȣ", JLabel.LEFT));
	    pwT = new JTextField(10);
	    pwP.add(pwT);
	      
	    Password(); // ��й�ȣ �Է½� ���� �ۼ� ����
	     
	    basicInfoP.add(nameP);
	    basicInfoP.add(addP);
	    basicInfoP.add(idP);
	    basicInfoP.add(pwP);
		
		//�߰� ����
	    addInfoP = new JPanel(new BorderLayout());
	    addInfoP.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "�߰� ����"));
	    
	    JPanel addPN = new JPanel(new GridLayout(2,1));
	    JPanel addPC = new JPanel();
	    
	    addInfoP.add(addPN, BorderLayout.NORTH);
	    addInfoP.add(addPC, BorderLayout.CENTER);
	    
	    //�߰����� - �з�
	    JPanel classifyP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    classifyP.add(new JLabel("��     ��"));
	    group_a1 = new JComboBox<String>(group_a);
	    classifyP.add(group_a1);
	    
	    //�߰����� - ��ȣ��
	    JPanel prefP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prefP.add(new JLabel("��ȣ��"));
        group_b1 = new JComboBox<String>(group_b);
        prefP.add(group_b1);

	    //�߰����� - �޸�
        JPanel memoP = new JPanel(new FlowLayout(FlowLayout.LEFT));       
        memoP.add(new JLabel("��  ��"));
        memoTA = new JTextArea(7,15);
        memoP.add(memoTA);
   
        addPN.add(classifyP);
        addPN.add(prefP);
        addPC.add(memoP);
        
        //��ư �г�
        btnP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnN = new JButton("���� �ۼ�(N)");
        btnI = new JButton("�Է�(I)");
        
        btnN.addActionListener(newinputbtn);
        btnI.addActionListener(inputbtn);
		btnI.setEnabled(false);
		
		btnP.add(btnN);
		btnP.add(btnI);
		
		//��ü��� ����
		JPanel totalP = new JPanel(new BorderLayout());
		totalP.add(basicInfoP, BorderLayout.NORTH);
		totalP.add(addInfoP, BorderLayout.SOUTH);
		
		createP.add(totalP, BorderLayout.NORTH);
		createP.add(btnP, BorderLayout.CENTER);
		
		this.add(createP, BorderLayout.WEST); // ��ü�� ����
		
		return createP;
        
	}
	
	private void Password() {
		if(pwT.getText().length() > 0)
			btnN.setEnabled(true);
	}
	
	
	
	// �� �г�
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		this.add(pane, BorderLayout.CENTER);
	    JPanel tabP = new JPanel();
	    tabP.setLayout(new BorderLayout());
	      
	    pane.addTab("����Ʈ ���", tabP);
	    pane.addTab("�����Ȳ", new JLabel("test"));
	    
	    // [�˻� / ����]
	    JPanel firstTabP = new JPanel();
	    tabP.add(firstTabP, BorderLayout.NORTH);
	    firstTabP.setLayout(new BorderLayout());
	    firstTabP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"�˻� / ����"));
	     
	    // [�˻� / ����] - �˻�
	    JPanel searchP = new JPanel();
	      
	    searchP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"�˻�"));
	    group_a2 = new JComboBox<String>(group_a);
	      
	    JLabel filter = new JLabel("���� : ");
	    filterT = new JTextField(7);
	      
	    searchP.add(group_a2);
	    searchP.add(filter);
	    searchP.add(filterT);
	    
	    // [�˻� / ����] - ����
		JPanel arrayP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		arrayP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"����")); // �׵θ�
	   

	    JPanel arrayPanel = new JPanel();
	    arrayP.add(arrayPanel, BorderLayout.SOUTH);
		
		group_c1 = new JComboBox<String>(group_c);
		group_c2 = new JComboBox<String>(group_c);
		
		arrayBtn = new JButton("����");
		basicBtn = new JButton("�⺻");
		
		arrayPanel.add(group_c1);
		arrayPanel.add(group_c2);
		arrayPanel.add(arrayBtn);
		arrayPanel.add(basicBtn);
	   

	    firstTabP.add(searchP,BorderLayout.WEST);
	    firstTabP.add(arrayP,BorderLayout.CENTER);
		
		// ǥ �г�
		tableP.setLayout(new BorderLayout());
	    tabP.add(tableP, BorderLayout.CENTER);
		
		
		// new DefaultTableModel(������,����)	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {"�з� ", "��ȣ��", "����Ʈ �̸�","����Ʈ �ּ�"});
		
	    table = new JTable(dtm);
		table.setRowHeight(40);
		tableP.add(new JScrollPane(table));
		
		
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		//tableRow(); // ǥ ���� Ŭ�� ��
	    table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				deleteBtn.setEnabled(true);
				int row = table.getSelectedRow();
				//Vector<String> siteinfo = data.get(row)
				HashMap<String, String> siteInfo = siteInfoList.get(row);
				
				nameT.setText(siteInfo.get("siteName").toString());
				addT.setText(siteInfo.get("siteUrl").toString());
				idT.setText(siteInfo.get("siteId").toString());
				group_a1.setSelectedItem(siteInfo.get("siteClass"));
				group_b1.setSelectedItem(siteInfo.get("sitePre"));
				memoTA.setText(siteInfo.get("siteMemo"));
				
				if(check.isSelected()==true) {

					idTF.setText(siteInfo.get("siteId").toString());
					pwTF.setText(siteInfo.get("sitePw").toString());
				
				}
				else if(check.isSelected()==false)
				{
					idTF.setText("");
					pwTF.setText("");
				}

				btnI.setEnabled(true);	
				btnI.setText("����(E)");
				
					
			}

			@Override
			public void mouseEntered(MouseEvent e) {	
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}});
		
		
		// �������� ����
		JPanel viewP = new JPanel(new BorderLayout()); // �������� ���� �г�
	    tabP.add(viewP, BorderLayout.SOUTH);
	     
	    check = new JCheckBox("�������� ����");
	  
	    
	    JLabel idL = new JLabel("���̵�");
	    idTF = new JTextField(7);
	    JLabel pwL = new JLabel("��й�ȣ");
	    pwTF = new JTextField(7);
	    deleteBtn = new JButton("����");
	    deleteBtn.setEnabled(false);
	    
	    deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = table.getSelectedRow();
				
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				
				if(n >= 0 && n < table.getRowCount())
					tm.removeRow(n);
				//�� ����
			}});
	    
	    check.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED) {
					int row = table.getSelectedRow();
					
					HashMap<String, String> getThisMapInfo = siteInfoList.get(row);
					
					idTF.setText(getThisMapInfo.get("siteId").toString());
					pwTF.setText(getThisMapInfo.get("sitePw").toString());
				}
				
				
			}
	    	
	    });
	    
	    JPanel p = new JPanel();
	    
	    p.add(check);
	    p.add(idL);
	    p.add(idTF);
	    p.add(pwL);
	    p.add(pwTF);
	    
	    viewP.add(p, BorderLayout.WEST);
	    viewP.add(deleteBtn, BorderLayout.EAST);
	    
	    
		return pane;
	}
	

	// ����Ʈ ���� �г�
	private JPanel SiteNumPanel() {
		JPanel numP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.add(numP, BorderLayout.SOUTH);
		numP.setBorder(BorderFactory.createLoweredBevelBorder());
		
		siteNumber = new JLabel();
		siteNumber.setText(dtm.getRowCount() + "���� ����Ʈ�� ��ϵǾ����ϴ�.");
		numP.add(siteNumber);
		
		return numP;
	}
	
	/*
	ActionListener input = new ActionListener() {
		//�������Ͽ��� ��������

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			
		}
		
	};
	ActionListener output = new ActionListener() {
		//�������Ͽ��� ��������
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	};
	*/
	
	ActionListener save = new ActionListener() {
		//����
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("c:\\"));
	        
			int ch = chooser.showSaveDialog(SiteInfoManagerApp.this);
			
			if (ch == JFileChooser.APPROVE_OPTION) {
				//�����ư ������
				String fileName = chooser.getSelectedFile().getName(); // ���� �̸�
				File fi = chooser.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(fi.getPath()+ ".dat");
					fw.write(fileName);
					fw.flush();
					fw.close();
				}catch(Exception e2) {
					
				}
	            //JOptionPane.showMessageDialog(SiteInfoManagerApp.this, fileName, "Ȯ��", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (ch == JFileChooser.CANCEL_OPTION) {
				//��ҹ�ư ������
				JOptionPane.showMessageDialog(SiteInfoManagerApp.this, "������ �������� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		}
	};
	
	ActionListener logout = new ActionListener() {
		//�α׾ƿ�
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Login login = new Login();
		}
		
	};
	ActionListener exit = new ActionListener() {
		//����
		@Override
		public void actionPerformed(ActionEvent e) {
			int bye = JOptionPane.showConfirmDialog(SiteInfoManagerApp.this, "������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
	        if (bye == JOptionPane.YES_OPTION) {
	            System.exit(0);
	         }
	        else return;
	         
		}
		
	};
	
	/*
	ActionListener user = new ActionListener() {
		//����� ����
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	};
	*/
	
	ActionListener classify = new ActionListener() {
		//����Ʈ �з�
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CategoryManageDialog category = new CategoryManageDialog();
			
		}
		
	};

	ItemListener autologin = new ItemListener() {
		//�ڵ� �α���
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			
		}
		
		
	};
	
	ItemListener remember = new ItemListener() {
		//�������� ���� ���� ����ϱ�
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
				check.setSelected(true);
			else
				check.setSelected(false);
			
		}
		
	};
	
	
	ActionListener newinputbtn = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("���� �ۼ�(N)")) {
				String sName = nameT.getText().toString();
				String sUrl = addT.getText().toString();
				String sClass = group_a1.getSelectedItem().toString();
				String sPre = group_b1.getSelectedItem().toString();
				String sId = idT.getText().toString();
				String sPw = pwT.getText().toString();
				String sMemo = memoTA.getText().toString();
				
				String[] sInfo = {sClass, sPre, sName, sUrl};
				dtm.addRow(sInfo);

				HashMap<String,String> siteDetailInfo = new HashMap<String,String>();
				
				siteDetailInfo.put("siteName", sName);
				siteDetailInfo.put("siteUrl", sUrl);
				siteDetailInfo.put("siteClass", sClass);
				siteDetailInfo.put("sitePre", sPre);
				siteDetailInfo.put("siteId", sId);
				siteDetailInfo.put("sitePw", sPw);
				siteDetailInfo.put("siteMemo",sMemo);
				
				siteInfoList.add(siteDetailInfo);
				
				
				
			}
			nameT.setText("");
			addT.setText("");
			group_a1.setSelectedIndex(0);
			group_b1.setSelectedIndex(0);
			idT.setText("");
			pwT.setText("");
			memoTA.setText("");
			
			siteNumber.setText(dtm.getRowCount()+"���� ����Ʈ�� ��ϵǾ����ϴ�.");
			
		}
		
	};
	ActionListener inputbtn = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				
				
				if(e.getActionCommand()=="����(E)"){
					
					String sName = nameT.getText().toString();
					String sUrl = addT.getText().toString();
					String sClass = group_a1.getSelectedItem().toString();
					String sPre = group_b1.getSelectedItem().toString();
					String sId = idT.getText().toString();
					String sPw = pwT.getText().toString();
					String sMemo = memoTA.getText().toString();
					
					String[] sInfo = {sClass, sPre, sName, sUrl};
					dtm.removeRow(index);
					dtm.insertRow(index, sInfo);
					
					HashMap<String,String> siteDetailInfo = new HashMap<String,String>();
					
					siteDetailInfo.put("siteName", sName);
					siteDetailInfo.put("siteUrl", sUrl);
					siteDetailInfo.put("siteClass", sClass);
					siteDetailInfo.put("sitePre", sPre);
					siteDetailInfo.put("siteId", sId);
					siteDetailInfo.put("sitePw", sPw);
					siteDetailInfo.put("siteMemo",sMemo);
					
					siteInfoList.add(siteDetailInfo);
					
					
				}

				nameT.setText("");
				addT.setText("");
				group_a1.setSelectedIndex(0);
				group_b1.setSelectedIndex(0);
				idT.setText("");
				pwT.setText("");
				memoTA.setText("");
				
				
			
		}
		
	};

	
	
	public static void main(String[] args) {
		new SiteInfoManagerApp();
	    Login login = new Login();
	}

}
