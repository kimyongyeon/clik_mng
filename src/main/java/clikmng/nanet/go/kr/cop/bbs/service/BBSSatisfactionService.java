package clikmng.nanet.go.kr.cop.bbs.service;

import java.util.Map;

/**
 * 만족도조사를 위한 서비스 인터페이스 클래스
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
public interface BBSSatisfactionService {
    /**
     * 만족도조사 사용 가능 여부를 확인한다.
     * 
     * @param bbsId
     * @return
     * @throws Exception
     */
    public boolean canUseSatisfaction(String bbsId) throws Exception;
    
    /**
     * 만족도조사에 대한 목록을 조회 한다.
     * 
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectSatisfactionList(SatisfactionVO satisfactionVO) throws Exception;
    
    /**
     * 만족도조사를 등록한다.
     * 
     * @param satisfaction
     * @throws Exception
     */
    public void insertSatisfaction(Satisfaction satisfaction) throws Exception;
    
    /**
     * 만족도조사를 삭제한다.
     * 
     * @param satisfactionVO
     * @throws Exception
     */
    public void deleteSatisfaction(SatisfactionVO satisfactionVO) throws Exception;
    
    /**
     * 만족도조사에 대한 내용을 조회한다.
     *      
     * @param satisfactionVO
     * @return
     * @throws Exception
     */
    public Satisfaction selectSatisfaction(SatisfactionVO satisfactionVO) throws Exception;
    
    /**
     * 만족도조사에 대한 내용을 수정한다.
     * 
     * @param satisfaction
     * @throws Exception
     */
    public void updateSatisfaction(Satisfaction satisfaction) throws Exception;
   
    /**
     * 만족도조사 패스워드를 가져온다.
     * 
     * @param satisfaction
     * @return
     * @throws Exception
     */
    public String getSatisfactionPassword(Satisfaction satisfaction) throws Exception;
}
