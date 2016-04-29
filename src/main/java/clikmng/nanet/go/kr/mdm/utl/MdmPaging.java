package clikmng.nanet.go.kr.mdm.utl;

import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;

/**
 * 회의록/의안/의원정보관리에서 페이징을 처리하는 클래스
 */
public class MdmPaging {

	int PageNum = 0;
	int TotalRecords = 0;
	int TotalPages = 0;
	int NoListPerPage = 0;
	int BlockPage = 0;
	int PagePerBlock = 0;
	int StartPage = 0;
	int EndPage = 0;
	int FirstRecord = 0;;
	int LastRecord = 0;
	
	String param = "";
	
	public int getFirstRecord() {
		return this.FirstRecord;
	}

	public int getLastRecord() {
		return this.LastRecord;
	}

	public int getTotalRecords() {
		return this.TotalRecords;
	}

	public int getTotalPages() {
		return this.TotalPages;
	}

	public String getParam() {
		return this.param;
	}

	public String setParam(String param) {
		return this.param = param;
	}

	public String setParam(MdmSearchVO vo) {
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);
		if( getIsNull(vo.getSchDt1()) ) {
			sb.append("&schDt1=").append(vo.getSchDt1());
		}
		if( getIsNull(vo.getSchDt2()) ) {
			sb.append("&schDt2=").append(vo.getSchDt2());
		}
		if( getIsNull(vo.getSchBrtcCode()) ) {
			sb.append("&schBrtcCode=").append(vo.getSchBrtcCode());
		}
		if( getIsNull(vo.getSchRks022()) ) {
			sb.append("&schRks022=").append(vo.getSchRks022());
		}
		if( getIsNull(vo.getSchLoAsmTyCode()) ) {
			sb.append("&schLoAsmTyCode=").append(vo.getSchLoAsmTyCode());
		}
		if( getIsNull(vo.getSchLoAsmCode()) ) {
			sb.append("&schLoAsmCode=").append(vo.getSchLoAsmCode());
		}
		if( getIsNull(vo.getSchRasmblyNumpr()) ) {
			sb.append("&schRasmblyNumpr=").append(vo.getSchRasmblyNumpr());
		}
		if( getIsNull(vo.getSchPprtyCode()) ) {
			sb.append("&schPprtyCode=").append(vo.getSchPprtyCode());
		}
		if( getIsNull(vo.getSchDuplication()) ) {
			sb.append("&schDuplication=").append(vo.getSchDuplication());
		}
		if( getIsNull(vo.getSchKey()) ) {
			sb.append("&schKey=").append(vo.getSchKey());
		}
		if( getIsNull(vo.getSchKw()) ) {
			sb.append("&schKw=").append(vo.getSchKw());
		}
		if( getIsNull(vo.getSchAsembyNm()) ) {
			sb.append("&schAsembyNm=").append(vo.getSchAsembyNm());
		}
		if( getIsNull(vo.getSchPprtyNm()) ) {
			sb.append("&schPprtyNm=").append(vo.getSchPprtyNm());
		}
		if( getIsNull(vo.getSelCondition()) ){
			sb.append("&selCondition=").append(vo.getSelCondition());
		}
		if( getIsNull(vo.getSchRegion()) ){
			sb.append("&schRegion=").append(vo.getSchRegion());
		}
		if( getIsNull(vo.getSchRegion2()) ){
			sb.append("&schRegion2=").append(vo.getSchRegion2());
		}
		if( getIsNull(vo.getSchSiteId()) ){
			sb.append("&schSiteId=").append(vo.getSchSiteId());
		}
		if( getIsNull(vo.getSchSeedId()) ){
			sb.append("&schSeedId=").append(vo.getSchSeedId());
		}
		
		sb.append("&listCnt=").append(vo.getListCnt());
		//sb.append("&pageNum=").append(vo.getPageNum());
		sb.append("&firstRecord=").append(this. getFirstRecord());
		sb.append("&lastRecord=").append(this.getLastRecord());
		return this.param = sb.toString();
	}

	public void setPagingCalc(int TotalRecords, int PageNum, int NoListPerPage) {
		this.TotalRecords = TotalRecords;
		this.NoListPerPage = NoListPerPage;
		this.PagePerBlock  = 10;

		if( PageNum == 0 ) {
			this.PageNum = 1;
		}
		else {
			this.PageNum = PageNum;
		}

		this.TotalPages = (int)Math.ceil(this.TotalRecords / (float)this.NoListPerPage);
		this.BlockPage  = (int)Math.ceil(this.PageNum / (float)this.PagePerBlock);

		this.StartPage = (this.BlockPage - 1) * this.PagePerBlock + 1;
		this.EndPage   = this.BlockPage * this.PagePerBlock;

		if (this.TotalPages < this.EndPage) {
			this.EndPage = this.TotalPages;
		}
		
		this.FirstRecord = (this.PageNum - 1) * this.NoListPerPage;
		/*
		this.LastRecord  = TotalRecords - this.FirstRecord;
		if (this.LastRecord > this.NoListPerPage) {
			this.LastRecord = this.NoListPerPage;
		}
		*/
		this.LastRecord = this.FirstRecord + this.NoListPerPage;
		if (this.LastRecord > this.TotalRecords) {
			this.LastRecord = this.TotalRecords;
		}

	}
	
	public String getPaging() {
		int i = 0;
		int prepage = 0;
		int nextpage = 0;
		
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		if (this.BlockPage >= 3) {
			prepage = this.StartPage - 1;
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
		    sb.append("<a href='?pageNum=1").append(this.param).append("'>&lt;&lt;</a> ");
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
			sb.append("<a href='?pageNum=").append(prepage).append(this.param).append("'>&lt;</a> ");
			sb.append("</li>");
		}
		else if (this.BlockPage == 2) {
			prepage = this.StartPage - 1;
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
			sb.append("<a href='?pageNum=").append(prepage).append(this.param).append("'>&lt;</a> ");
			sb.append("</li>");
		}
		 
		for(i = this.StartPage; i <= this.EndPage; i++) {
			if (i == this.PageNum) {
				sb.append("<li class='paginate_button active' aria-controls='dataTables-example' tabindex='0'>");
				sb.append("<a href='#;'>").append(i).append("</a>");
				sb.append("</li>");
			}
			else {
				sb.append("<li class='paginate_button' aria-controls='dataTables-example' tabindex='0'>");
				sb.append("<a href='?pageNum=").append(i).append(this.param).append("'>").append(i).append("</a>");
				sb.append("</li>");
			}
		}

		if (this.TotalPages > this.EndPage) {
			nextpage = this.EndPage + 1;
			sb.append("<li class='paginate_button next' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_next'>");
			sb.append("<a href='?pageNum=").append(nextpage).append(this.param).append("'>&gt;</a> ");
		}

		if ((this.TotalPages - this.EndPage) > 10) {
			sb.append("<li class='paginate_button next' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_next'>");
			sb.append("<a href='?pageNum=").append(this.TotalPages).append(this.param).append("'>&gt;&gt;</a>");
		}
		return sb.toString();
	}
	
	public String getPaging(MdmSearchVO vo) {
		int i = 0;
		int prepage = 0;
		int nextpage = 0;
		
		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		if (this.BlockPage >= 3) {
			prepage = this.StartPage - 1;
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
			sb.append("<a href='#;' onclick='paging(1);'>&lt;&lt;</a> ");
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
			sb.append("<a href='#;' onclick='paging(").append(prepage).append(");'>&lt;</a> ");
			sb.append("</li>");
		}
		else if (this.BlockPage == 2) {
			prepage = this.StartPage - 1;
			sb.append("<li class='paginate_button previous' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_previous'>");
			sb.append("<a href='#;' onclick='paging(").append(prepage).append(");'>&lt;</a> ");
			sb.append("</li>");
		}
		 
		for(i = this.StartPage; i <= this.EndPage; i++) {
			if (i == this.PageNum) {
				sb.append("<li class='paginate_button active' aria-controls='dataTables-example' tabindex='0'>");
				sb.append("<a href='#;'>").append(i).append("</a>");
				sb.append("</li>");
			}
			else {
				sb.append("<li class='paginate_button' aria-controls='dataTables-example' tabindex='0'>");
				sb.append("<a href='#;' onclick='paging(").append(i).append(");'>").append(i).append("</a>");
				sb.append("</li>");
			}
		}

		if (this.TotalPages > this.EndPage) {
			nextpage = this.EndPage + 1;
			sb.append("<li class='paginate_button next' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_next'>");
			sb.append("<a href='#;' onclick='paging(").append(nextpage).append(");'>&gt;</a> ");
		}

		if ((this.TotalPages - this.EndPage) > 10) {
			sb.append("<li class='paginate_button next' aria-controls='dataTables-example' tabindex='0' id='dataTables-example_next'>");
			sb.append("<a href='#;' onclick='paging(").append(this.TotalPages).append(");'>&gt;&gt;</a>");
		}
		
		sb.append("<form name='pgfrm' method='post' enctype='multipart/form-data'>");
		sb.append("<input type='hidden' name='schDt1' value='").append(vo.getSchDt1()).append("'>");
		sb.append("<input type='hidden' name='schDt2' value='").append(vo.getSchDt2()).append("'>");
		sb.append("<input type='hidden' name='schDt3' value='").append(vo.getSchDt3()).append("'>");
		sb.append("<input type='hidden' name='schDt4' value='").append(vo.getSchDt4()).append("'>");
		sb.append("<input type='hidden' name='schBrtcCode' value='").append(vo.getSchBrtcCode()).append("'>");
		sb.append("<input type='hidden' name='schRks022' value='").append(vo.getSchRks022()).append("'>");
		sb.append("<input type='hidden' name='schRks025' value='").append(vo.getSchRks025()).append("'>");
		sb.append("<input type='hidden' name='schRks026' value='").append(vo.getSchRks026()).append("'>");
		sb.append("<input type='hidden' name='schLoAsmTyCode' value='").append(vo.getSchLoAsmTyCode()).append("'>");
		sb.append("<input type='hidden' name='schLoAsmCode' value='").append(vo.getSchLoAsmCode()).append("'>");
		sb.append("<input type='hidden' name='schRasmblyNumpr' value='").append(vo.getSchRasmblyNumpr()).append("'>");
		sb.append("<input type='hidden' name='schPprtyCode' value='").append(vo.getSchPprtyCode()).append("'>");
		sb.append("<input type='hidden' name='schDuplication' value='").append(vo.getSchDuplication()).append("'>");
		sb.append("<input type='hidden' name='schKey' value='").append(vo.getSchKey()).append("'>");
		sb.append("<input type='hidden' name='schKw' value='").append(vo.getSchKw()).append("'>");
		sb.append("<input type='hidden' name='schAsembyNm' value='").append(vo.getSchAsembyNm()).append("'>");
		sb.append("<input type='hidden' name='schPprtyNm' value='").append(vo.getSchPprtyNm()).append("'>");
		
		sb.append("<input type='hidden' name='schEstCode' value='").append(vo.getSchEstCode()).append("'>");
		sb.append("<input type='hidden' name='schEstNm' value='").append(vo.getSchEstNm()).append("'>");
		
		sb.append("<input type='hidden' name='listCnt' value='").append(vo.getListCnt()).append("'>");
		sb.append("<input type='hidden' name='pageNum' id='pageNum' value=''>");
		sb.append("<input type='hidden' name='firstRecord' value='").append(this. getFirstRecord()).append("'>");
		sb.append("<input type='hidden' name='lastRecord' value='").append(this.getLastRecord()).append("'>");
		sb.append("<input type='hidden' name='rasmblyId' id='rasmblyId' value='").append(vo.getRasmblyId()).append("'>");
		sb.append("<input type='hidden' name='rasmblyNumpr' id='rasmblyNumpr' value='").append(vo.getRasmblyNumpr()).append("'>");
		sb.append("<input type='hidden' name='asembyId' id='asembyId' value='").append(vo.getAsembyId()).append("'>");
		sb.append("<input type='hidden' name='biId' id='biId' value='").append(vo.getBiId()).append("'>");
		sb.append("<input type='hidden' name='extId' id='extId' value='").append(vo.getExtId()).append("'>");
		sb.append("<input type='hidden' name='cnId' id='cnId' value='").append(vo.getCnId()).append("'>");
		
		sb.append("<input type='hidden' name='schMtgNm' value='").append(vo.getSchMtgNm()).append("'>");
		sb.append("<input type='hidden' name='schContent' value='").append(vo.getSchContent()).append("'>");
		sb.append("<input type='hidden' name='schApp' value='").append(vo.getSchApp()).append("'>");
		sb.append("<input type='hidden' name='schBiSj' value='").append(vo.getSchBiSj()).append("'>");
		sb.append("<input type='hidden' name='schPropsr' value='").append(vo.getSchPropsr()).append("'>");
		sb.append("<input type='hidden' name='schJrsdCmitId' value='").append(vo.getSchJrsdCmitId()).append("'>");
		
		sb.append("<input type='hidden' name='schBiKndStdCd' id='schBiKndStdCd' value='").append(vo.getSchBiKndStdCd()).append("'>");
		sb.append("<input type='hidden' name='schLastResultClStdCd' id='schLastResultClStdCd' value='").append(vo.getSchLastResultClStdCd()).append("'>");
		sb.append("<input type='hidden' name='schConversion' id='schConversion' value='").append(vo.getSchConversion()).append("'>");
		
		sb.append("<input type='hidden' name='schOrgCodeStep1' id='schOrgCodeStep1' value='").append(vo.getSchOrgCodeStep1()).append("'>");
		sb.append("<input type='hidden' name='schOrgCodeStep2' id='schOrgCodeStep2' value='").append(vo.getSchOrgCodeStep2()).append("'>");
		sb.append("<input type='hidden' name='schOrgCodeStep3' id='schOrgCodeStep3' value='").append(vo.getSchOrgCodeStep3()).append("'>");

		sb.append("<input type='hidden' name='selCondition' id='selCondition' value='").append(vo.getSelCondition()).append("'>");
		sb.append("<input type='hidden' name='schRegion'  id='schRegion'  value='").append(vo.getSchRegion()).append("'>");
		sb.append("<input type='hidden' name='schRegion2'  id='schRegion2'  value='").append(vo.getSchRegion2()).append("'>");
		sb.append("<input type='hidden' name='schSiteId'  id='schSiteId'  value='").append(vo.getSchSiteId()).append("'>");
		sb.append("<input type='hidden' name='schSeedId'  id='schSeedId'  value='").append(vo.getSchSeedId()).append("'>");
		sb.append("<input type='hidden' name='schIsView'  id='schIsView'  value='").append(vo.getSchIsView()).append("'>");
		sb.append("<input type='hidden' name='schDel'     id='schDel'  value='").append(vo.getSchDel()).append("'>");

		sb.append("<input type='hidden' name='schDocType' id='schDocType' value='").append(vo.getSchDocType()).append("'>");
		sb.append("<input type='hidden' name='schTitle' id='schTitle' value='").append(vo.getSchTitle()).append("'>");
		sb.append("<input type='hidden' name='mdmAdm' id='mdmAdm' value='").append(vo.getMdmAdm()).append("'>");
		sb.append("<input type='hidden' name='schDflt' id='schDflt' value='").append(vo.getSchDflt()).append("'>");
		sb.append("<input type='hidden' name='disfile' id='disfile' value='").append(vo.getDisfile()).append("'>");
		
		sb.append("<input type='hidden' name='schOrgCodeStep4' id='schOrgCodeStep4' value='").append(vo.getSchOrgCodeStep4()).append("'>");
		sb.append("<input type='hidden' name='schFile' id='schFile' value='").append(vo.getSchFile()).append("'>");
		sb.append("<input type='hidden' name='sort' value='").append(vo.getSort()).append("'>");
		
		sb.append("<input type='hidden' name='excelSearchCnList' value=\"").append(vo.getExcelSearchCnList()).append("\">");
		
		sb.append("</form>");
		return sb.toString();
	}

	public boolean getIsNull(String str) {
		boolean flg = true;
		if( str == null || str.equals("") ) {
			flg = false;
		}
	    return flg;
	}
}
