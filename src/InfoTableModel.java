
import javax.swing.table.AbstractTableModel;

public class InfoTableModel extends AbstractTableModel{
	String[] columnNames = {"�з�", "��ȣ��", "����Ʈ �̸�", "����Ʈ �ּ�"};
	
	
	Object[][] data = {	};
	
	
	// Head ���
	@Override
	public String getColumnName(int column) { return columnNames[column]; }
	
	@Override
	public int getColumnCount() { return columnNames.length; }

	@Override
	public int getRowCount() { return data.length; }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { return data[rowIndex][columnIndex]; }
	
	//AbstractTableModel�� ���� �Ļ�
	//HashSet<SiteInfo> data; //SiteInfoList�� ���� �ڷ���
	//SiteInfoList�� �Ű������� ���� ������ ����
	//SiteInfo ����� �� ������ ���� �޼��� �߰�

}
