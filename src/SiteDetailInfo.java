import java.util.Vector;

public class SiteDetailInfo extends SiteInfo{
	private String classify;
	private String prefer;
	private String memo;
	
	Vector<SiteDetailInfo> detailInfos = new Vector<SiteDetailInfo>();
	
	SiteDetailInfo(String name, String url, String id, String pw, String classify, String prefer, String memo) {
		super(name, url, id, pw);
		
		this.classify = classify;
		this.prefer = prefer;
		this.memo = memo;
	}


	String getClassify() { return classify; }
	String getPrefer() { return prefer; }
	String getMemo() { return memo; }
	
	void setClassify(String classify) { this.classify = classify; }
	void setPrefer(String prefer) { this.prefer = prefer; }
	void setMemo(String memo) { this.memo = memo; }
	

	public Vector<SiteDetailInfo> getSiteDetailInfo(){
		return detailInfos;
	}
	
}
