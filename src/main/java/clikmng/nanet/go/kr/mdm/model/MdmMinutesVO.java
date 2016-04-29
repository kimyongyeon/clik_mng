package clikmng.nanet.go.kr.mdm.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

public class MdmMinutesVO {
	
	/**
	 * 
	 * 회의록 정보를 처리하는 VO 클래스
	 * @author 회의록 개발 최태광
	 * @since 2014.10.31
	 * @version 1.0
	 * @see
	 *
	 * <pre>
	 * << 개정이력(Modification Information) >>
	 *   
	 *   수정일      수정자         수정내용
	 *  -------    --------    --------------------
	 *   014.10.31  최태광          최초 생성
	 *
	 * </pre>
	 */
	
	private int muid      = 0;
	private int mdaesu    = -1;
	private int myear     = -1;
	private int mss       = -1;
	private int mchasu;
	private String mcode  = "";
	private String mfile  = "";
	private int    mdate1 = -1;
	private String mdate2 = "";
	private String mgubun = "";
	private String mangun = "N";
	private String metc1  = "";  
	private String metc2  = "";  
	private String metc3  = "";  
	private String metc4  = "";  
	private String metc5  = "";  
	private String metc6  = "";
	
	private String mchk   = "N";
	private String mregid = "";
	private String mregname = "";
	private String mregdate = "";
	private String mmodifyid   = "";
	private String mmodifyname = "";
	private String mmodifydate = "";
	private String mip = "";	

	private int datepos    = -1;
	private int agendapos1 = -1;  
	private int agendapos2 = -1;  

	private int opentime   = -1;
	private int closetime  = -1;
	private int totalLines = 0;
	
	private int opendiv  = 0;
	private int closediv = 0;

	private String mode    = "";
	private String mtype   = "";
	private String mindate = "";
	private String maxdate = "";
	private String daesudate1 = "";
	private String daesudate2 = "";

	private String mtgnmkndstdcd = ""; // 회의명_종류_표준코드 MNK001=본회의, MNK002=상임위원회, MNK003=특별위원회, MNK004=행정사무감사/조사
	private String today         = "";
	private String rasmblyid     = ""; // 의회코드
	private String spkrkndstdcd  = ""; // 발엊자_종류_코드   SKK001=의원, SKK002=기타
	private String spkngkndstdcd = ""; // 발언_종류_표준코드 SPK001=intro, SPK002=안건, SPK003=시/도정질문, SPK004=시/도답변, SPK005=5분자유발언, SPK900=기타
	private String sesnsestdcd   = "SES100"; // SES100=일반회기, SES200=행정사무감사/조사
	private String closeat       = "N";      // Y or N
	private String othbcstdcd    = "공개";    // 공개_표준코드
	private String openingtime = "";
	private String closingtime = "";
		
	private String content;
	private String[][] lines;
	private HashMap<String, String> councilman = new HashMap<String, String>();
	private String[] angunErr;
	private String[][] appendix;
	private String[][] angun2;
	private LinkedHashMap<String, String> scontent = new LinkedHashMap<String, String>();
	private LinkedHashMap<Integer, String> angun1 = new LinkedHashMap<Integer, String>();
	
	public Vector<String> speaker1 = new Vector<String>();
	public Vector<String> speaker2 = new Vector<String>();
	public Vector<String> speaker3 = new Vector<String>();

	private Vector<String> err = new Vector<String>();
	
	public int getMuid() {
		return muid;
	}
	public void setMuid(int muid) {
		this.muid = muid;
	}
	public int getMdaesu() {
		return mdaesu;
	}
	public void setMdaesu(int mdaesu) {
		this.mdaesu = mdaesu;
	}
	public int getMyear() {
		return myear;
	}
	public void setMyear(int myear) {
		this.myear = myear;
	}
	public int getMss() {
		return mss;
	}
	public void setMss(int mss) {
		this.mss = mss;
	}
	public int getMchasu() {
		return mchasu;
	}
	public void setMchasu(int mchasu) {
		this.mchasu = mchasu;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getMfile() {
		return mfile;
	}
	public void setMfile(String mfile) {
		this.mfile = mfile;
	}
	public int getMdate1() {
		return mdate1;
	}
	public void setMdate1(int mdate1) {
		this.mdate1 = mdate1;
	}
	public String getMdate2() {
		return mdate2;
	}
	public void setMdate2(String mdate2) {
		this.mdate2 = mdate2;
	}
	public String getMgubun() {
		return mgubun;
	}
	public void setMgubun(String mgubun) {
		this.mgubun = mgubun;
	}
	public String getMangun() {
		return mangun;
	}
	public void setMangun(String mangun) {
		this.mangun = mangun;
	}
	public String getMetc1() {
		return metc1;
	}
	public void setMetc1(String metc1) {
		this.metc1 = metc1;
	}
	public String getMetc2() {
		return metc2;
	}
	public void setMetc2(String metc2) {
		this.metc2 = metc2;
	}
	public String getMetc3() {
		return metc3;
	}
	public void setMetc3(String metc3) {
		this.metc3 = metc3;
	}
	public String getMetc4() {
		return metc4;
	}
	public void setMetc4(String metc4) {
		this.metc4 = metc4;
	}
	public String getMetc5() {
		return metc5;
	}
	public void setMetc5(String metc5) {
		this.metc5 = metc5;
	}
	public String getMetc6() {
		return metc6;
	}
	public void setMetc6(String metc6) {
		this.metc6 = metc6;
	}
	public String getMchk() {
		return mchk;
	}
	public void setMchk(String mchk) {
		this.mchk = mchk;
	}
	public String getMregid() {
		return mregid;
	}
	public void setMregid(String mregid) {
		this.mregid = mregid;
	}
	public String getMregname() {
		return mregname;
	}
	public void setMregname(String mregname) {
		this.mregname = mregname;
	}
	public String getMregdate() {
		return mregdate;
	}
	public void setMregdate(String mregdate) {
		this.mregdate = mregdate;
	}
	public String getMmodifyid() {
		return mmodifyid;
	}
	public void setMmodifyid(String mmodifyid) {
		this.mmodifyid = mmodifyid;
	}
	public String getMmodifyname() {
		return mmodifyname;
	}
	public void setMmodifyname(String mmodifyname) {
		this.mmodifyname = mmodifyname;
	}
	public String getMmodifydate() {
		return mmodifydate;
	}
	public void setMmodifydate(String mmodifydate) {
		this.mmodifydate = mmodifydate;
	}
	public String getMip() {
		return mip;
	}
	public void setMip(String mip) {
		this.mip = mip;
	}
	public Vector<String> getSpeaker1() {
		return speaker1;
	}
	public void setSpeaker1(Vector<String> speaker1) {
		this.speaker1 = speaker1;
	}
	public Vector<String> getSpeaker2() {
		return speaker2;
	}
	public void setSpeaker2(Vector<String> speaker2) {
		this.speaker2 = speaker2;
	}
	public Vector<String> getSpeaker3() {
		return speaker3;
	}
	public void setSpeaker3(Vector<String> speaker3) {
		this.speaker3 = speaker3;
	}
	public int getDatepos() {
		return datepos;
	}
	public void setDatepos(int datepos) {
		this.datepos = datepos;
	}
	public int getAgendapos1() {
		return agendapos1;
	}
	public void setAgendapos1(int agendapos1) {
		this.agendapos1 = agendapos1;
	}
	public int getAgendapos2() {
		return agendapos2;
	}
	public void setAgendapos2(int agendapos2) {
		this.agendapos2 = agendapos2;
	}
	public int getOpentime() {
		return opentime;
	}
	public void setOpentime(int opentime) {
		this.opentime = opentime;
	}
	public int getClosetime() {
		return closetime;
	}
	public void setClosetime(int closetime) {
		this.closetime = closetime;
	}
	public int getTotalLines() {
		return totalLines;
	}
	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}
	public int getOpendiv() {
		return opendiv;
	}
	public void setOpendiv(int opendiv) {
		this.opendiv = opendiv;
	}
	public int getClosediv() {
		return closediv;
	}
	public void setClosediv(int closediv) {
		this.closediv = closediv;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getMindate() {
		return mindate;
	}
	public void setMindate(String mindate) {
		this.mindate = mindate;
	}
	public String getMaxdate() {
		return maxdate;
	}
	public void setMaxdate(String maxdate) {
		this.maxdate = maxdate;
	}
	public String getDaesudate1() {
		return daesudate1;
	}
	public void setDaesudate1(String daesudate1) {
		this.daesudate1 = daesudate1;
	}
	public String getDaesudate2() {
		return daesudate2;
	}
	public void setDaesudate2(String daesudate2) {
		this.daesudate2 = daesudate2;
	}
	public String getMtgnmkndstdcd() {
		return mtgnmkndstdcd;
	}
	public void setMtgnmkndstdcd(String mtgnmkndstdcd) {
		this.mtgnmkndstdcd = mtgnmkndstdcd;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getRasmblyid() {
		return rasmblyid;
	}
	public void setRasmblyid(String rasmblyid) {
		this.rasmblyid = rasmblyid;
	}
	public String getSpkrkndstdcd() {
		return spkrkndstdcd;
	}
	public void setSpkrkndstdcd(String spkrkndstdcd) {
		this.spkrkndstdcd = spkrkndstdcd;
	}
	public String getSpkngkndstdcd() {
		return spkngkndstdcd;
	}
	public void setSpkngkndstdcd(String spkngkndstdcd) {
		this.spkngkndstdcd = spkngkndstdcd;
	}
	public String getSesnsestdcd() {
		return sesnsestdcd;
	}
	public void setSesnsestdcd(String sesnsestdcd) {
		this.sesnsestdcd = sesnsestdcd;
	}
	public String getCloseat() {
		return closeat;
	}
	public void setCloseat(String closeat) {
		this.closeat = closeat;
	}
	public String getOthbcstdcd() {
		return othbcstdcd;
	}
	public void setOthbcstdcd(String othbcstdcd) {
		this.othbcstdcd = othbcstdcd;
	}
	public String getOpeningtime() {
		return openingtime;
	}
	public void setOpeningtime(String openingtime) {
		this.openingtime = openingtime;
	}
	public String getClosingtime() {
		return closingtime;
	}
	public void setClosingtime(String closingtime) {
		this.closingtime = closingtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[][] getLines() {
		return lines;
	}
	public void setLines(String[][] lines) {
		this.lines = lines;
	}
	public LinkedHashMap<String, String> getScontent() {
		return scontent;
	}
	public void setScontent(LinkedHashMap<String, String> scontent) {
		this.scontent = scontent;
	}
	public LinkedHashMap<Integer, String> getAngun1() {
		return angun1;
	}
	public void setAngun1(LinkedHashMap<Integer, String> angun1) {
		this.angun1 = angun1;
	}
	public String[][] getAngun2() {
		return angun2;
	}
	public void setAngun2(String[][] angun2) {
		this.angun2 = angun2;
	}
	public HashMap<String, String> getCouncilman() {
		return councilman;
	}
	public void setCouncilman(HashMap<String, String> councilman) {
		this.councilman = councilman;
	}
	public String[] getAngunErr() {
		return angunErr;
	}
	public void setAngunErr(String[] angunErr) {
		this.angunErr = angunErr;
	}
	public String[][] getAppendix() {
		return appendix;
	}
	public void setAppendix(String[][] appendix) {
		this.appendix = appendix;
	}
	public Vector<String> getErr() {
		return err;
	}
	public void setErr(Vector<String> err) {
		this.err = err;
	}

}
