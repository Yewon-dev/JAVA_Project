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
	//main 메서드를 가짐 
	//SiteInfoManager 객체 생성

	ArrayList<HashMap<String,String>> siteInfoList = new ArrayList<HashMap<String,String>>();
	

	
	//TablePanel
	private JPanel tableP = new JPanel();
	
	//createSiteInfo
	private JPanel createP, basicInfoP, addInfoP, btnP, searchP, arrayP;
	private JTextField nameT, addT, idT, pwT;
	private JComboBox<String> group_a1, group_b1;
	private JButton btnN, btnI;
	private JTextArea memoTA;
	
	private String[] group_a = {"분류", "일반", "학교", "정보", "포털"};
	private String[] group_b = {"미지정", "☆", "☆☆", "☆☆☆", "☆☆☆☆", "☆☆☆☆☆"};
	private String[] group_c = {"전체", "분류", "선호도", "사이트 이름", "사이트 주소"};
	
	//TabPanel
	private JTextField filterT;
	private JComboBox<String> group_a2, group_c1, group_c2;
	private JButton arrayBtn, basicBtn;
	
	//개인정보보기 Panel
	private JCheckBox check;
	private JButton deleteBtn;
	private JLabel siteNumber;
	private JTextField idTF, pwTF;
	
	
	protected JTable table;
	
	protected DefaultTableModel dtm;
	
	SiteInfoManagerApp() {
		super("계정관리 프로그램 :) by.yewon");
		

		Menu(); //상단의 메뉴
		SiteInfoPanel(); // [입력 / 수정] 패널
		
		JTabbedPane pane = createTabbedPane(); // Tab
		this.add(pane, BorderLayout.CENTER);
		
		SiteNumPanel(); // 사이트 개수 패널
		
		
	    setSize(900, 700);
	    setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//메뉴바
	private void Menu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		//메인 메뉴
		JMenu mF = new JMenu("파일(F)");
		JMenu mM = new JMenu("관리(M)");
		JMenu mS = new JMenu("설정(S)");
		
		menubar.add(mF);
		menubar.add(mM);
		menubar.add(mS);
		
		//서브 메뉴 - 파일
		JMenuItem fI = new JMenuItem("엑셀 파일에서 가져오기(I)");
		JMenuItem fE = new JMenuItem("엑셀 파일에서 내보내기(E)");
		JMenuItem fS = new JMenuItem("저장(S)");
		JMenuItem fO = new JMenuItem("로그아웃(O)");
		JMenuItem fX = new JMenuItem("종료(X)");
		
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
		
		//서브 메뉴 - 관리
		JMenuItem mU = new JMenuItem("사용자(U)");
		JMenuItem mC = new JMenuItem("사이트 분류(C)");
		
		//mU.addActionListener(user);
		mU.setEnabled(false);
		mC.addActionListener(classify);
		
		mM.add(mU);
		mM.add(mC);
		
		mU.setMnemonic('U');
		mC.setMnemonic('C');
		
		//서브 메뉴 - 설정
		JCheckBoxMenuItem sL = new JCheckBoxMenuItem("자동 로그인(L)");
		JCheckBoxMenuItem sV = new JCheckBoxMenuItem("계정정보 보기 상태 기억하기 (V)");
		
		sL.addItemListener(autologin);
		sV.addItemListener(remember);
		
		mS.add(sL);
		mS.add(sV);	
	
		sL.setMnemonic('L');
		sV.setMnemonic('V');
		
	}
	private JPanel SiteInfoPanel() {
		
		//정보 입력 패널
		createP = new JPanel(new BorderLayout());
		createP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"입력 / 수정"));
		
		//기본 정보
		basicInfoP = new JPanel();
		basicInfoP.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "기본 정보"));
	    basicInfoP.setLayout(new GridLayout(4,1));
	    	
	    //기본정보 - 사이트명
		JPanel nameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameP.add(new JLabel("사이트명"));
		nameT = new JTextField(14);
		nameP.add(nameT);
		
		//기본정보 - 사이트 주소
		JPanel addP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    addP.add(new JLabel("주소(URL)  http://", JLabel.LEFT));
	    addT = new JTextField(10);
	    addP.add(addT);

	    //기본정보 - 아이디
	    JPanel idP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    idP.add(new JLabel("아  이  디", JLabel.LEFT));
	    idT = new JTextField(10);
	    idP.add(idT);
	     
	    //기본정보 - 비밀번호
	    JPanel pwP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    pwP.add(new JLabel("비밀번호", JLabel.LEFT));
	    pwT = new JTextField(10);
	    pwP.add(pwT);
	      
	    Password(); // 비밀번호 입력시 새로 작성 가능
	     
	    basicInfoP.add(nameP);
	    basicInfoP.add(addP);
	    basicInfoP.add(idP);
	    basicInfoP.add(pwP);
		
		//추가 정보
	    addInfoP = new JPanel(new BorderLayout());
	    addInfoP.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "추가 정보"));
	    
	    JPanel addPN = new JPanel(new GridLayout(2,1));
	    JPanel addPC = new JPanel();
	    
	    addInfoP.add(addPN, BorderLayout.NORTH);
	    addInfoP.add(addPC, BorderLayout.CENTER);
	    
	    //추가정보 - 분류
	    JPanel classifyP = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    classifyP.add(new JLabel("분     류"));
	    group_a1 = new JComboBox<String>(group_a);
	    classifyP.add(group_a1);
	    
	    //추가정보 - 선호도
	    JPanel prefP = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prefP.add(new JLabel("선호도"));
        group_b1 = new JComboBox<String>(group_b);
        prefP.add(group_b1);

	    //추가정보 - 메모
        JPanel memoP = new JPanel(new FlowLayout(FlowLayout.LEFT));       
        memoP.add(new JLabel("메  모"));
        memoTA = new JTextArea(7,15);
        memoP.add(memoTA);
   
        addPN.add(classifyP);
        addPN.add(prefP);
        addPC.add(memoP);
        
        //버튼 패널
        btnP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnN = new JButton("새로 작성(N)");
        btnI = new JButton("입력(I)");
        
        btnN.addActionListener(newinputbtn);
        btnI.addActionListener(inputbtn);
		btnI.setEnabled(false);
		
		btnP.add(btnN);
		btnP.add(btnI);
		
		//전체페널 설정
		JPanel totalP = new JPanel(new BorderLayout());
		totalP.add(basicInfoP, BorderLayout.NORTH);
		totalP.add(addInfoP, BorderLayout.SOUTH);
		
		createP.add(totalP, BorderLayout.NORTH);
		createP.add(btnP, BorderLayout.CENTER);
		
		this.add(createP, BorderLayout.WEST); // 전체의 왼쪽
		
		return createP;
        
	}
	
	private void Password() {
		if(pwT.getText().length() > 0)
			btnN.setEnabled(true);
	}
	
	
	
	// 탭 패널
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		this.add(pane, BorderLayout.CENTER);
	    JPanel tabP = new JPanel();
	    tabP.setLayout(new BorderLayout());
	      
	    pane.addTab("사이트 목록", tabP);
	    pane.addTab("등록현황", new JLabel("test"));
	    
	    // [검색 / 정렬]
	    JPanel firstTabP = new JPanel();
	    tabP.add(firstTabP, BorderLayout.NORTH);
	    firstTabP.setLayout(new BorderLayout());
	    firstTabP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"검색 / 정렬"));
	     
	    // [검색 / 정렬] - 검색
	    JPanel searchP = new JPanel();
	      
	    searchP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"검색"));
	    group_a2 = new JComboBox<String>(group_a);
	      
	    JLabel filter = new JLabel("필터 : ");
	    filterT = new JTextField(7);
	      
	    searchP.add(group_a2);
	    searchP.add(filter);
	    searchP.add(filterT);
	    
	    // [검색 / 정렬] - 정렬
		JPanel arrayP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		arrayP.setBorder(new TitledBorder(new LineBorder(Color.black,1),"정렬")); // 테두리
	   

	    JPanel arrayPanel = new JPanel();
	    arrayP.add(arrayPanel, BorderLayout.SOUTH);
		
		group_c1 = new JComboBox<String>(group_c);
		group_c2 = new JComboBox<String>(group_c);
		
		arrayBtn = new JButton("정렬");
		basicBtn = new JButton("기본");
		
		arrayPanel.add(group_c1);
		arrayPanel.add(group_c2);
		arrayPanel.add(arrayBtn);
		arrayPanel.add(basicBtn);
	   

	    firstTabP.add(searchP,BorderLayout.WEST);
	    firstTabP.add(arrayP,BorderLayout.CENTER);
		
		// 표 패널
		tableP.setLayout(new BorderLayout());
	    tabP.add(tableP, BorderLayout.CENTER);
		
		
		// new DefaultTableModel(데이터,제목열)	
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[] {"분류 ", "선호도", "사이트 이름","사이트 주소"});
		
	    table = new JTable(dtm);
		table.setRowHeight(40);
		tableP.add(new JScrollPane(table));
		
		
		
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		
		//tableRow(); // 표 행을 클릭 시
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
				btnI.setText("수정(E)");
				
					
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
		
		
		// 계정정보 보기
		JPanel viewP = new JPanel(new BorderLayout()); // 계정정보 보기 패널
	    tabP.add(viewP, BorderLayout.SOUTH);
	     
	    check = new JCheckBox("계정정보 보기");
	  
	    
	    JLabel idL = new JLabel("아이디");
	    idTF = new JTextField(7);
	    JLabel pwL = new JLabel("비밀번호");
	    pwTF = new JTextField(7);
	    deleteBtn = new JButton("삭제");
	    deleteBtn.setEnabled(false);
	    
	    deleteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int n = table.getSelectedRow();
				
				DefaultTableModel tm = (DefaultTableModel) table.getModel();
				
				if(n >= 0 && n < table.getRowCount())
					tm.removeRow(n);
				//행 삭제
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
	

	// 사이트 개수 패널
	private JPanel SiteNumPanel() {
		JPanel numP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.add(numP, BorderLayout.SOUTH);
		numP.setBorder(BorderFactory.createLoweredBevelBorder());
		
		siteNumber = new JLabel();
		siteNumber.setText(dtm.getRowCount() + "개의 사이트가 등록되었습니다.");
		numP.add(siteNumber);
		
		return numP;
	}
	
	/*
	ActionListener input = new ActionListener() {
		//엑셀파일에서 가져오기

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			
		}
		
	};
	ActionListener output = new ActionListener() {
		//엑셀파일에서 내보내기
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	};
	*/
	
	ActionListener save = new ActionListener() {
		//저장
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser(new File("c:\\"));
	        
			int ch = chooser.showSaveDialog(SiteInfoManagerApp.this);
			
			if (ch == JFileChooser.APPROVE_OPTION) {
				//저장버튼 누르면
				String fileName = chooser.getSelectedFile().getName(); // 파일 이름
				File fi = chooser.getSelectedFile();
				try {
					FileWriter fw = new FileWriter(fi.getPath()+ ".dat");
					fw.write(fileName);
					fw.flush();
					fw.close();
				}catch(Exception e2) {
					
				}
	            //JOptionPane.showMessageDialog(SiteInfoManagerApp.this, fileName, "확인", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (ch == JFileChooser.CANCEL_OPTION) {
				//취소버튼 누르면
				JOptionPane.showMessageDialog(SiteInfoManagerApp.this, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
	            return;
			}
		}
	};
	
	ActionListener logout = new ActionListener() {
		//로그아웃
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Login login = new Login();
		}
		
	};
	ActionListener exit = new ActionListener() {
		//종료
		@Override
		public void actionPerformed(ActionEvent e) {
			int bye = JOptionPane.showConfirmDialog(SiteInfoManagerApp.this, "정말로 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
	        if (bye == JOptionPane.YES_OPTION) {
	            System.exit(0);
	         }
	        else return;
	         
		}
		
	};
	
	/*
	ActionListener user = new ActionListener() {
		//사용자 정보
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	};
	*/
	
	ActionListener classify = new ActionListener() {
		//사이트 분류
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CategoryManageDialog category = new CategoryManageDialog();
			
		}
		
	};

	ItemListener autologin = new ItemListener() {
		//자동 로그인
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			
		}
		
		
	};
	
	ItemListener remember = new ItemListener() {
		//계정정보 보기 상태 기억하기
		
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
			if (e.getActionCommand().equals("새로 작성(N)")) {
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
			
			siteNumber.setText(dtm.getRowCount()+"개의 사이트가 등록되었습니다.");
			
		}
		
	};
	ActionListener inputbtn = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				
				
				if(e.getActionCommand()=="수정(E)"){
					
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
