import java.util.Vector;

public class SiteInfoList {
	//HashSet<SiteInfo> infos; // 또는 TReeSet
	//여러 웹 사이트들에 대한 계정 정보 유지 책임
	


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
