package clikmng.nanet.go.kr.mna.ram.service;

import java.util.List;

/**
 * 권한별 롤 관리에 대한 Vo 클래스를 정의한다.
 * @author
 * @since 
 * @version 
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 */

public class AuthorRoleManageVO extends AuthorRoleManage {

	private static final long serialVersionUID = 1L;

	List <AuthorRoleManageVO> authorRoleList;
	/**
	 * AuthorRoleManage 를 리턴한다.
	 * @return AuthorRoleManage
	 */
	public AuthorRoleManage getAuthorRole()
    {
    	return super.getAuthorRole();
    }
	/**
	 * AuthorRoleManage 값을 설정한다.
	 * @param authorRoleManage AuthorRoleManage 
	 */
    public void setAuthorRole(AuthorRoleManage authorRoleManage)
    {
    	super.setAuthorRole(authorRoleManage);
    }
	/**
	 * authorRoleList attribute 를 리턴한다.
	 * @return List<AuthorRoleManageVO>
	 */
	public List<AuthorRoleManageVO> getAuthorRoleList() {
		return authorRoleList;
	}

	/**
	 * authorRoleList attribute 값을 설정한다.
	 * @param authorRoleList List<AuthorRoleManageVO> 
	 */
	public void setAuthorRoleList(List<AuthorRoleManageVO> authorRoleList) {
		this.authorRoleList = authorRoleList;
	}



}