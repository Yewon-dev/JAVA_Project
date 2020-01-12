import java.util.HashSet;

public class SiteInfo {
	//�ϳ��� �� ����Ʈ�� ���� ���� ���� ���� å��
		
	private String name;
	private String url;
	private String id;
	private String pw;
	
	public SiteInfo(String name, String url, String id, String pw) {
		this.name = name;
		this.url = url;
		this.id = id;
		this.pw = pw;
	}


	String getName() { return name; }
	String getUrl() { return url; }
	String getId() { return id; }
	String getPw() { return pw; }
	
	void setName(String name) { this.name = name; }
	void setUrl(String url) { this.url = url; }
	void setId(String id) { this.id = id; }
	void setPw(String pw) { this.pw = pw; }
	
	
	// �ߺ� ����
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SiteInfo) {
			SiteInfo s = (SiteInfo) obj;
			return name.equals(s.name);
		}
		return false;
	}
	
}
