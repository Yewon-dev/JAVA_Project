import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class CategoryManageDialog extends JFrame{
	
	private JButton deleteBtn, addBtn, newInputBtn, endBtn;
	private JList registlist;
	private JTextField nameT;   
	
	private Vector<String> cat = new Vector<String>();
	private DefaultListModel listModel;
			
	

	public CategoryManageDialog() {
		setTitle("사이트 분류 관리 by.yewon");


	    setSize(600,500);
		this.setLayout(new GridLayout(1,2));
	    this.add(RegistList());
	    this.add(Edit());
	      
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	     
	    setVisible(true);
	   }
	   
	   
	private JPanel RegistList() { //등록항목
		JPanel p = new JPanel(new BorderLayout());
	    JPanel jp = new JPanel();
	    
	    p.setBorder(new TitledBorder(new LineBorder(Color.black,1),"등록항목"));
	    
	    registlist = new JList(cat); 
	    

	    cat.addElement("정보");
	    cat.addElement("포털");
	    cat.addElement("학교");
	    
	    p.add(registlist, BorderLayout.CENTER);
	    p.add(jp, BorderLayout.SOUTH);


				
	    
	    return p;
	   }
	
	 // 편집 내용
	 private JPanel Edit() {
	    JPanel editP1 = new JPanel(new BorderLayout());
	    editP1.setLayout(new BorderLayout());
	    
	    JPanel editP2 = new JPanel();
	    editP2.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "편집내용"));
	      
	    
	    JPanel editLabel = new JPanel();
	    editLabel.setLayout(new GridLayout(2,1));
	      
	    // 항목 이름
	    JPanel pl = new JPanel();
	    pl.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JLabel ListName = new JLabel("항목이름 ");
	    nameT = new JTextField(14);
	    pl.add(ListName);
	    pl.add(nameT);
	      
	    // 삭제  추가 버튼
	    JPanel pb = new JPanel();
	    pb.setLayout(new FlowLayout(FlowLayout.CENTER));
	    
	    deleteBtn = new JButton("삭제(D)");
	    addBtn = new JButton("추가(A)");
	    
	    pb.add(deleteBtn);
	    pb.add(addBtn);
	    
	    deleteBtn.setMnemonic('D');
	    addBtn.setMnemonic('A');
	      
	    deleteBtn.addActionListener(new ActionListener() {
	    	
	         @Override
	         public void actionPerformed(ActionEvent e) {
	        	 int index = registlist.getSelectedIndex();
	        	 cat.remove(index);
	        	 
	        	 registlist.setListData(cat);
	        	 
	        	 if(cat.size() == 0)
	        		 deleteBtn.setEnabled(false);
	         }
	         
	    });
	    
	    addBtn.addActionListener(new ActionListener() {
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				String newName = nameT.getText();

				cat.add(newName);
				nameT.setText("");
				registlist.setListData(cat);
				
			}
	    	
	    });
	      
	      
	  
	    // 신규 완료 버튼
	    JPanel EndP = new JPanel();
	    newInputBtn = new JButton("신규(N)");
	    endBtn = new JButton("완료(C)");
	    newInputBtn.setMnemonic('N');
	    endBtn.setMnemonic('C');
	      
	    
	    // 패널에 부착
	    EndP.add(newInputBtn);
	    EndP.add(endBtn);

	    editLabel.add(pl);
	    editLabel.add(pb);
	      
	    editP2.add(editLabel, BorderLayout.NORTH);
	    editP1.add(editP2, BorderLayout.CENTER);
	    editP1.add(EndP, BorderLayout.SOUTH) ;

	    return editP1; 
	   }
	   
	   public static void main(String[] args) {
	      new CategoryManageDialog();
	   }
}
