import java.util.Vector;

public class SiteInfoList {
	//HashSet<SiteInfo> infos; // �Ǵ� TReeSet
	//���� �� ����Ʈ�鿡 ���� ���� ���� ���� å��
	


	static Vector<SiteInfo> infos = new Vector<SiteInfo>();
	
	public void add(SiteInfo data) {
		infos.add(data);
	}
	public void delete(SiteInfo data) {
		infos.remove(data);
	}
	
	public static Vector<SiteInfo> getSiteInfo(){
		return infos;
	}
	
	

}
