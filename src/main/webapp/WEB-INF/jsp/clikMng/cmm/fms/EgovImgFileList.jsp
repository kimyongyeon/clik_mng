<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovImgFileList.jsp
  * @Description : 이미지 파일 조회화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  *
  *  @author 
  *  @since 
  *  @version 
  *  @see
  *
  */
%>
<title>이미지파일목록</title>

	<table>
      	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	      <tr>
	      	<td></td>
	      </tr>
	      <tr>
	       <td>
	       		<img style="width:230px; height:150;" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=<c:out value="${fileVO.fileSn}"/>&fileExtsn=<c:out value="${fileVO.fileExtsn}"/>&streFileNm=<c:out value="${fileVO.streFileNm}"/>&fileStreCours=<c:out value="${fileVO.fileStreCours}"/>'  alt="해당파일이미지"/>
	       </td>
	      </tr>
	      <tr>
	      	<td></td>
	      </tr>
        </c:forEach>
      </table>
