
import javax.swing.table.AbstractTableModel;

public class InfoTableModel extends AbstractTableModel{
	String[] columnNames = {"분류", "선호도", "사이트 이름", "사이트 주소"};
	
	
	Object[][] data = {	};
	
	
	// Head 출력
	@Override
	public String getColumnName(int column) { return columnNames[column]; }
	
	@Override
	public int getColumnCount() { return columnNames.length; }

	@Override
	public int getRowCount() { return data.length; }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { return data[rowIndex][columnIndex]; }
	
	//AbstractTableModel로 부터 파생
	//HashSet<SiteInfo> data; //SiteInfoList와 같은 자료형
	//SiteInfoList를 매개변수로 갖는 생성자 가짐
	//SiteInfo 기반의 셀 데이터 접근 메서드 추가

}
