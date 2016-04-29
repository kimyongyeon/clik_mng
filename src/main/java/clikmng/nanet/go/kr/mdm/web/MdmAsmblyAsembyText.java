package clikmng.nanet.go.kr.mdm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;
import clikmng.nanet.go.kr.mdm.model.MdmTnsrAsmblyAsembyActVO;

public class MdmAsmblyAsembyText extends AbstractView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
										HttpServletRequest httpservletrequest,
										HttpServletResponse response) throws Exception {
		
		MdmSearchVO 					searchVO		=	(MdmSearchVO)map.get("searchVO");
		List<MdmTnsrAsmblyAsembyActVO>	resultList		=	(List<MdmTnsrAsmblyAsembyActVO>)map.get("resultList");
		
		try {
			// 파일명
			String txtFile = "지방의회의원_메타데이터";
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
			sb.append("일련번호\t의회명\t의원명\t대수\t정당명\t선거구\t사진유무\t중복\t게시\t문서번호\tt서비스URL\r\n");
			
			MdmTnsrAsmblyAsembyActVO vo = null;
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				vo = resultList.get(i);
				
				sb.append((i+1));
				sb.append("\t");
				sb.append(vo.getRASMBLY_NM()!=null ? vo.getRASMBLY_NM() : "");
				sb.append("\t");				
				sb.append(vo.getASEMBY_NM()!=null ? vo.getASEMBY_NM() : "");
				sb.append("\t");
				sb.append(vo.getRASMBLY_NUMPR());
				sb.append("\t");
				sb.append(vo.getPPRTY_NM()!=null ? vo.getPPRTY_NM() : "");
				sb.append("\t");
				sb.append(vo.getEST_NM()!=null ? vo.getEST_NM() : "");
				sb.append("\t");
				if(vo.getPHOTO_FILE_PATH() != null && vo.getPHOTO_FILE_PATH() != ""){
					sb.append("Y");
				}else{
					sb.append("N");
				}
				sb.append("\t");
				sb.append(vo.getDUPCNT() > 1 ? "중복(" + vo.getDUPCNT() + ")" : "");
				sb.append("\t");
				if(vo.getISVIEW() != null && vo.getISVIEW().equals("N")){
					sb.append("미게시");
				}else{
					sb.append("게시");
				}
				sb.append("\t");
				sb.append(vo.getASEMBY_CN());
				sb.append("\t");
				sb.append("http://clik.nanet.go.kr/potal/search/searchView.do?collection=assemblyinfo&DOCID="+vo.getASEMBY_CN());
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