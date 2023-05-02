package co.poetrypainting.service;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {
	@Autowired
	private SampleService service;
	
	@Test
	public void testExist(){
		assertNotNull(service);
	}
	@Test
	public void testAddData() throws Exception{
		String data = "마크의 책은 모든 투자자의 책꽂이에 있어야 한다. -데이비드 라이언, 3회 연속 전미투자대회 우승자 《초수익 성장주 투자》는 ‘투자의 신’이라 불리는 마크 미너비니의 국내 첫 번역";
		byte[] bs = data.getBytes("utf-8");
		log.info(bs.length);
		byte[] bs2 = new byte[50];
		System.arraycopy(bs, 0, bs2, 0, 50);
		log.info(bs2.length);
		String str = new String(bs2, "utf-8");
		log.info(str);
		//data = "abcd";
		//log.info(data.length());
//		service.addDate(data);
	}
}
