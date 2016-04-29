package clikmng.nanet.go.kr.mdm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyBiVO;

public class MdmBillText extends AbstractView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
										HttpServletRequest httpservletrequest,
										HttpServletResponse response) throws Exception {
		
		MdmSearchVO 					searchVO		=	(MdmSearchVO)map.get("searchVO");
		List<MdmTnsrAsmblyBiVO>	resultList		=	(List<MdmTnsrAsmblyBiVO>)map.get("resultList");
		
		try {
			// 파일명
			String txtFile = "지방의회의안_메타데이터";
			StringBuilder txtFileNm = new StringBuilder();
			
			txtFileNm.append(URLEncoder.encode(txtFile, "UTF-8"));
			txtFileNm.append("(");		
			
			if(searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false)
			{
				txtFileNm.append(searchVO.getSchDt1());
			}
			if( (searchVO.getSchDt1() != null && "".equals(searchVO.getSchDt1()) == false) ||
				(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false) )
			{
				txtFileNm.append("~");			
			}
			if(searchVO.getSchDt2() != null && "".equals(searchVO.getSchDt2()) == false)
			{
				txtFileNm.append(searchVO.getSchDt2());
			}
			
			txtFileNm.append(")");
			txtFileNm.append(".txt");
			
			response.setContentType("text/plain; charset=UTF-8");
		    response.setHeader("Content-Disposition","attachment;filename="+txtFileNm+"");
			
			StringBuffer sb = new StringBuffer();
			//sb.append("일련번호\t의회명\t대수\t의안번호\t의안명\t소관의원회\t제안자\t첨부파일유무\t제안일\t수집일\t삭제\t중복\t게시\t문서번호\t서비스URL\r\n");
			sb.append("일련번호\t의회명\t대수\t의안번호\t의안명\t소관의원회\t제안자\t제안일\t수집일\t삭제\t중복\t게시\t문서번호\t서비스URL\r\n");
			
			MdmTnsrAsmblyBiVO vo = null;
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				vo = resultList.get(i);
				
				sb.append((i+1));
				sb.append("\t");
				sb.append(vo.getRASMBLY_NM()!=null ? vo.getRASMBLY_NM() : "");
				sb.append("\t");				
				sb.append(vo.getRASMBLY_NUMPR());
				sb.append("\t");
				sb.append(vo.getBI_NO()!=null ? vo.getBI_NO() : "");
				sb.append("\t");
				sb.append(vo.getBI_SJ()!=null ? vo.getBI_SJ() : "");
				sb.append("\t");
				sb.append(vo.getMTGNM()!=null ? vo.getMTGNM() : "");
				sb.append("\t");
				sb.append(vo.getPROPSR()!=null ? vo.getPROPSR() : "");
//				sb.append("\t");
//				if(vo.getFILECNT() > 0) {
//					String value = "";
//					if(vo.getFILESUCCCNT() > 0 ) {
//						value += "변환성공(" + vo.getFILESUCCCNT() + ")";
//					}
//					if(vo.getFILEFLRCNT() > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환실패(" + vo.getFILEFLRCNT() + ")";
//					}
//					if((vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) > 0 ) {
//						value += value.length() != 0 ? " / " : "";
//						value += "변환대기(" + (vo.getFILECNT() - vo.getFILESUCCCNT() - vo.getFILEFLRCNT()) + ")";
//					}
//					sb.append(value);
//				}
				sb.append("\t");
				sb.append(CommonUtil.formatDate(vo.getITNC_DE()!=null ? vo.getITNC_DE() : "","-"));
				sb.append("\t");
				sb.append(CommonUtil.formatDate(vo.getFRST_REGIST_DT()!=null ? vo.getFRST_REGIST_DT() : "","-"));
				sb.append("\t");
				sb.append(vo.getCUD_CODE().equals("D") ? "삭제" : "");
				sb.append("\t");
				sb.append(vo.getDUPCNT() > 1 ? "중복(" + vo.getDUPCNT() + ")" : "");
				sb.append("\t");
				if(vo.getISVIEW() != null && vo.getISVIEW().equals("N")){
					sb.append("미게시");
				}else{
					sb.append("게시");
				}
				sb.append("\t");
				sb.append(vo.getBI_CN());
				sb.append("\t");
				sb.append("http://clik.nanet.go.kr/potal/search/searchView.do?collection=bill&DOCID="+vo.getBI_CN());
				sb.append("\r\n");
				
			}
				
			String mag = new String(sb.toString().getBytes("UTF-8"), "ISO-8859-1");
			
		    ServletOutputStream out = response.getOutputStream();
		    out.println(mag);
		    out.flush();
		    out.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}