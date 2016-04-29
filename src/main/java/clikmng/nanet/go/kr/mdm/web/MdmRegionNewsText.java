package clikmng.nanet.go.kr.mdm.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import clikmng.nanet.go.kr.cmm.CommonUtil;
import clikmng.nanet.go.kr.mdm.model.MdmRegionNewsVO;
import clikmng.nanet.go.kr.mdm.model.MdmSearchVO;

public class MdmRegionNewsText extends AbstractView {
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map,
										HttpServletRequest httpservletrequest,
										HttpServletResponse response) throws Exception {
		
		MdmSearchVO 					searchVO		=	(MdmSearchVO)map.get("searchVO");
		List<MdmRegionNewsVO>	resultList		=	(List<MdmRegionNewsVO>)map.get("resultList");
		
		
		try {
			// 파일명
			String txtFile = "지역현안소식_메타데이터";
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
			sb.append("일련번호\t수집기관\t지역\t제목\t작성자\t등록일자\t수집일자\t중복\t게시\t문서번호\t서비스URL\r\n");
			
			MdmRegionNewsVO vo = null;
			
			for( int i = 0 ; i < resultList.size(); i++ )
			{
				vo = resultList.get(i);
				
				vo.setINDT(vo.getINDT().replace(".", "").replace("/", "").replace("-", ""));
				vo.setREGDATE(vo.getREGDATE().replace(".", "").replace("/", "").replace("-", ""));
				
				sb.append((i+1));
				sb.append("\t");
				sb.append(vo.getSEED_NM()!=null ? vo.getSEED_NM() : "");
				sb.append("\t");				
				sb.append(vo.getREGION_NM()!=null ? vo.getREGION_NM() : "");
				sb.append("\t");
				sb.append(vo.getTITLE()!=null ? vo.getTITLE() : "");
				sb.append("\t");
				sb.append(vo.getWRITER()!=null ? vo.getWRITER() : "");
				sb.append("\t");
				sb.append(CommonUtil.formatDate(vo.getINDT()!=null ? vo.getINDT() : "","-"));
				sb.append("\t");
				sb.append(CommonUtil.formatDate(vo.getREGDATE()!=null ? vo.getREGDATE() : "","-"));
				sb.append("\t");
				sb.append(vo.getDUPCNT() > 1 ? "중복(" + vo.getDUPCNT() + ")" : "");
				sb.append("\t");
				if(vo.getISVIEW() != null && vo.getISVIEW().equals("N")){
					sb.append("미게시");
				}else{
					sb.append("게시");
				}
				sb.append("\t");
				sb.append(vo.getNEWS_ID());
				sb.append("\t");
				sb.append("http://clik.nanet.go.kr/potal/search/searchView.do?collection=news&DOCID="+vo.getNEWS_ID());
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