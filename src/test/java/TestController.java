import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kimyongyeon on 2016-04-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/clik/spring/com/context-common.xml",
        "classpath:/clik/spring/com/context-datasource.xml",
        "classpath:/clik/spring/com/context-sqlMap.xml",
        "classpath:/clik/spring/com/context-transaction.xml",
        "classpath:/clik/spring/com/context-aspect.xml",
        "classpath:/clik/spring/com/context-egovuserdetailshelper.xml",
        "classpath:/clik/spring/com/context-excel.xml",
        "classpath:/clik/spring/com/context-idgen.xml",
        "classpath:/clik/spring/com/context-properties.xml",
        "classpath:/clik/spring/com/context-scheduling-crawling-news.xml",
        "classpath:/clik/spring/com/context-scheduling-sym-log-ulg.xml",
        "classpath:/clik/spring/com/context-scheduling-sym-log-wlg.xml",
        "classpath:/clik/spring/com/context-syslogaop.xml",
        "classpath:/clik/spring/com/context-transaction.xml",
        "classpath:/clik/spring/com/context-validator.xml",
        "file:src/main/webapp/WEB-INF/config/clik/springmvc/clik-com-stsaop.xml",
        "file:src/main/webapp/WEB-INF/config/clik/springmvc/clik-com-interceptor.xml",
        "file:src/main/webapp/WEB-INF/config/clik/springmvc/clik-com-loginaop.xml",
        "file:src/main/webapp/WEB-INF/config/clik/springmvc/clik-com-servlet.xml"})
public class TestController {

    @Before
    public void setUp() {
        System.out.println("선거구 관리자 테스트 시작");
    }

    @Test
    public void insertRasmblyEst() throws Exception {
        System.out.println("선거구 등록");
    }

    @Test
    public void updateRasmblyEst() throws Exception {
        System.out.println("선거구 수정");
    }

    @Test
    public void deleteRasmblyEst() throws Exception {
        System.out.println("선거구 삭제");
    }

    @Test
    public void selectRasmblyEst() {
        System.out.println("선거구 조회");
    }

}
