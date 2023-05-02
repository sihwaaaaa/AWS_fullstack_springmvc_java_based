package co.poetrypainting.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.poetrypainting.config.RootConfig;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
@Log4j
public class TimeMapperTests {
	@Autowired
	private TimeMapper timeMapper;
	
	
	@Test
	public void testGetTime(){
		log.info(timeMapper.getTime());
	}
}
