package clikmng.nanet.go.kr.mdm.model;


/**
 * 
 * 사이트정보를 처리하는 VO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
public class MdmNewsVO extends MdmManageDefaultVO {
	
    private static final long serialVersionUID = 1L;
    
    private String 	newsId;
    private String 	regdate;
    private String 	upDt;
    private String 	delDt;
    private String 	region;
    private String 	regionNm;
    private String 	writer;
    private String 	title;
    private String 	content;
    private String 	cud;
    private String 	auto;
    private String 	atchFileId;
    private String 	seedId;
    private String 	seedNm;
    private String 	articleId;
    private String 	isView;
    private String 	indt;
    
    

    public MdmNewsVO()
    {
        newsId = "";
        regdate = "";
        upDt = "";
        delDt = "";
        region = "";
        regionNm = "";
        writer = "";
        title = "";
        content = "";
        cud = "";
        auto = "";
        atchFileId = "";
        seedId = "";
        seedNm = "";
        articleId = "";
        indt = "";
        isView = "0";
    }

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getUpDt() {
		return upDt;
	}

	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}

	public String getDelDt() {
		return delDt;
	}

	public void setDelDt(String delDt) {
		this.delDt = delDt;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCud() {
		return cud;
	}

	public void setCud(String cud) {
		this.cud = cud;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getSeedId() {
		return seedId;
	}

	public void setSeedId(String seedId) {
		this.seedId = seedId;
	}

	public String getSeedNm() {
		return seedNm;
	}

	public void setSeedNm(String seedNm) {
		this.seedNm = seedNm;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	
	
	public String getIsView() {
		return isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegionNm() {
		return regionNm;
	}

	public void setRegionNm(String regionNm) {
		this.regionNm = regionNm;
	}

	public String getIndt() {
		return indt;
	}

	public void setIndt(String indt) {
		this.indt = indt;
	}

    
    
    
    
    
}
