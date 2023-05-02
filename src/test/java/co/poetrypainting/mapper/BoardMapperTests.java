package co.poetrypainting.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.poetrypainting.config.RootConfig;
import co.poetrypainting.domain.BoardVo;
import co.poetrypainting.domain.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList(){
		boardMapper.getList().forEach(log::info);
	}
	@Test
	public void testGetListWithPaging(){
		boardMapper.getListWithPaging(new Criteria(3, 10, "TW", "1")).forEach(log::info);
	}
	@Test
	public void testInsert(){
		BoardVo vo = new BoardVo();
		vo.setTitle("테스트 코드 작성 insert() 제목");
		vo.setContent("테스트 코드 작성 insert() 내용");
		vo.setWriter("작성자");
		boardMapper.insert(vo);
		log.info(vo);
	}
	@Test
	public void testInsertSelectKey(){
		BoardVo vo = new BoardVo();
		vo.setTitle("테스트 코드 작성 insertSelectKey() 제목");
		vo.setContent("테스트 코드 작성 insertSelectKey() 내용");
		vo.setWriter("작성자");
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	@Test
	public void testRead(){
		Long bno = 327717L;
//		Long bno = 327702L;
		log.info(boardMapper.read(bno));
	}
	
	@Test
	public void testDelete(){
		Long bno = 19L;
		log.info(boardMapper.read(bno));
		log.info(boardMapper.delete(bno));
		log.info(boardMapper.read(bno));
	}
	@Test
	public void testUpdate(){
		BoardVo vo = boardMapper.read(3L);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setWriter("user00");
		
		log.info(vo);
		boardMapper.update(vo);
		
		BoardVo vo2 = boardMapper.read(3L);
		log.info(vo);
		
//		assertSame(vo, vo2);
	}
	
	@Test
	public void testGetTotalCnt(){
		int cnt = boardMapper.getTotalCnt(new Criteria());
		log.info(cnt);
		int cnt2 = boardMapper.getTotalCnt(new Criteria(1, 10, "TW", "1"));
		log.info(cnt2);
	}
}
