<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<input type='hidden' name='schDt1' value='${mdmSearchVO.schDt1}' />
	<input type='hidden' name='schDt2' value='${mdmSearchVO.schDt2}' />
	<input type='hidden' name='schDt3' value='${mdmSearchVO.schDt3}' />
	<input type='hidden' name='schDt4' value='${mdmSearchVO.schDt4}' />
	<input type='hidden' name='schBrtcCode' value='${mdmSearchVO.schBrtcCode}' />
	<input type='hidden' name='schRks022' value='${mdmSearchVO.schRks022}' />
	<input type='hidden' name='schRks025' value='${mdmSearchVO.schRks025}' />
	<input type='hidden' name='schRks026' value='${mdmSearchVO.schRks026}' />
	<input type='hidden' name='schLoAsmTyCode' value='${mdmSearchVO.schLoAsmTyCode}' />
	<input type='hidden' name='schLoAsmCode' value='${mdmSearchVO.schLoAsmCode}' />
	<input type='hidden' name='schRasmblyNumpr' value='${mdmSearchVO.schRasmblyNumpr}' />
	<input type='hidden' name='schPprtyCode' value='${mdmSearchVO.schPprtyCode}' />
	<input type='hidden' name='schDuplication' value='${mdmSearchVO.schDuplication}' />
	<input type='hidden' name='schKey' value='${mdmSearchVO.schKey}' />
	<input type='hidden' name='schKw' value='${mdmSearchVO.schKw}' />
	<input type='hidden' name='schAsembyNm' value='${mdmSearchVO.schAsembyNm}' />
	<input type='hidden' name='schPprtyNm' value='${mdmSearchVO.schPprtyNm}' />
	<input type='hidden' name='listCnt' value='${mdmSearchVO.listCnt}' />
	<input type='hidden' name='pageNum' id='pageNum' value='${mdmSearchVO.pageNum}' />
	<input type='hidden' name='firstRecord' value='${mdmSearchVO.firstRecord}' />
	<input type='hidden' name='lastRecord'  value='${mdmSearchVO.lastRecord}' />
	<input type='hidden' name='rasmblyId' id='rasmblyId' value='${mdmSearchVO.rasmblyId}' />
	<input type='hidden' name='rasmblyNumpr' id='rasmblyNumpr' value='${mdmSearchVO.rasmblyNumpr}' />
	<input type='hidden' name='biId' id='biId' value='${mdmSearchVO.biId}' />
	<input type='hidden' name='cnId' id='cnId' value='${mdmSearchVO.cnId}' />
	<input type='hidden' name='schBiKndStdCd' id='schBiKndStdCd' value='${mdmSearchVO.schBiKndStdCd}' />
	<input type='hidden' name='schLastResultClStdCd' id='schLastResultClStdCd' value='${mdmSearchVO.schLastResultClStdCd}' />

	<input type='hidden' name='schMtgNm'      value='${mdmSearchVO.schMtgNm}' />
	<input type='hidden' name='schContent'    value='${mdmSearchVO.schContent}' />
	<input type='hidden' name='schApp'        value='${mdmSearchVO.schApp}' />
	<input type='hidden' name='schBiSj'       value='${mdmSearchVO.schBiSj}' />
	<input type='hidden' name='schPropsr'     value='${mdmSearchVO.schPropsr}' />
	<input type='hidden' name='schJrsdCmitId' value='${mdmSearchVO.schJrsdCmitId}' />

	<input type='hidden' name='schOrgCodeStep1' id='schOrgCodeStep1' value='${mdmSearchVO.schOrgCodeStep1}' />
	<input type='hidden' name='schOrgCodeStep2' id='schOrgCodeStep2' value='${mdmSearchVO.schOrgCodeStep2}' />
	<input type='hidden' name='schOrgCodeStep3' id='schOrgCodeStep3' value='${mdmSearchVO.schOrgCodeStep3}' />
	<input type='hidden' name='schOrgCodeStep4' id='schOrgCodeStep4' value='${mdmSearchVO.schOrgCodeStep4}' />
	<input type='hidden' name='selCondition' id='selCondition' value='${mdmSearchVO.selCondition}' />
	<input type='hidden' name='schRegion'  id='schRegion'  value='${mdmSearchVO.schRegion}' />
	<input type='hidden' name='schRegion2' id='schRegion2'  value='${mdmSearchVO.schRegion2}' />
	<input type='hidden' name='schSiteId'  id='schSiteId'  value='${mdmSearchVO.schSiteId}' />
	<input type='hidden' name='schSeedId'  id='schSeedId'  value='${mdmSearchVO.schSeedId}' />
	<input type='hidden' name='schIsView'  id='schIsView'  value='${mdmSearchVO.schIsView}' />
	<input type='hidden' name='schDel'     id='schDel'     value='${mdmSearchVO.schDel}' />
	<input type='hidden' name='schDocType' id='schDocType' value='${mdmSearchVO.schDocType}' />
	<input type='hidden' name='schTitle' id='schTitle' value='${mdmSearchVO.schTitle}' />
	<input type='hidden' name='mdmAdm'   id='mdmAdm'   value='${mdmSearchVO.mdmAdm}' />
	<input type='hidden' name='schDflt'  id='schDflt'  value='${mdmSearchVO.schDflt}' />
	<input type='hidden' name='disfile'  id='disfile'  value='${mdmSearchVO.disfile}' />
		
	<input type='hidden' name='schConversion' id='schConversion' value='${mdmSearchVO.schConversion}' />
	<input type='hidden' name='schFile' id='schFile' value='${mdmSearchVO.schFile}' />