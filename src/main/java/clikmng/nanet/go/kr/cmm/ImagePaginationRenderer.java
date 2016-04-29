package clikmng.nanet.go.kr.cmm;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
/**
 * ImagePaginationRenderer.java 클래스
 * 
 * @author 
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    -------------    ----------------------
 *  
 * </pre>
 */
public class ImagePaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;
	
	public ImagePaginationRenderer() {
	}
	
	public void initVariables(){
		//firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"" + servletContext.getContextPath() +  "/images/clikMng/cmm/mod/icon/icon_prevend.gif\" alt=\"처음\"   border=\"0\"/></a>&#160;";
		firstPageLabel    = "<li class=\"paginate_button first\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">처음</a></li>&#160;";		
		//firstPageLabel ="";
        previousPageLabel = "<li class=\"paginate_button previous\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">Previous</a></li>&#160;";
        currentPageLabel  = "<li class=\"paginate_button active\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a> {0} </a></li>&#160;";
        otherPageLabel    = "<li class=\"paginate_button\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a></li>&#160;";
        nextPageLabel     = "<li class=\"paginate_button next \" aria-controls=\"dataTables-example\" tabindex=\"0\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">Next</a></li>&#160;";
        lastPageLabel     = "<li class=\"paginate_button last\" aria-controls=\"dataTables-example\" tabindex=\"0\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">마지막</a></li>&#160;";
        //lastPageLabel ="";
	}

	

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

}
