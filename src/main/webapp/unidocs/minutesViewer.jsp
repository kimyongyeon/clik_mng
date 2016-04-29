<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="clikmng.nanet.go.kr.mdm.utl.*" %>
<%
try {
	MdmRequestUtil rq = new MdmRequestUtil(request);
	String RASMBLY_ID = rq.getRequest("RASMBLY_ID", "");
	String RASMBLY_SESN = rq.getRequest("RASMBLY_SESN", "");
	String MTGNM_ID   = rq.getRequest("MTGNM_ID", "");
	String MTG_DE     = rq.getRequest("MTG_DE", "");
	int MINTS_ODR  = rq.getRequestInt("MINTS_ODR", -1);
	response.sendRedirect("/mdm/MdmMinutesView.do?RASMBLY_ID="+RASMBLY_ID+"&RASMBLY_SESN="+RASMBLY_SESN+"&MTGNM_ID="+MTGNM_ID+"&MTG_DE="+MTG_DE+"&MINTS_ODR="+MINTS_ODR); 
}
catch(Exception e) {
	out.print(e.toString());
	out.print("회의록 고유 아이디가 없습니다.");
}
%>