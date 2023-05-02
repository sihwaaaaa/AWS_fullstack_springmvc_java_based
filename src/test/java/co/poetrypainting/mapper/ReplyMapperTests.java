package co.poetrypainting.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.poetrypainting.domain.BoardVo;
import co.poetrypainting.domain.Criteria;
import co.poetrypainting.domain.ReplyVo;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	//댓글 50개를 최근 게시글 5개에 10개씩 작성
	@Test
	public void testCreate(){
		List<BoardVo> boards = boardMapper.getListWithPaging(new Criteria(1, 5));
		boards.forEach(log::info);
		
		IntStream.rangeClosed(1, 50).forEach(i -> {
			ReplyVo vo = new ReplyVo();
			vo.setBno(boards.get(i%5).getBno());
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("tester" + i);
			
			replyMapper.insert(vo);
					
		});
	
	}
	@Test
	public void testRead(){
		Long rno = 3L;
		log.info(replyMapper.read(rno));
	}
	
	@Test
	public void testDelete(){
		Long rno = 2L;
		log.info(replyMapper.delete(rno));
	}
	@Test
	public void testUpdate(){
		ReplyVo vo = replyMapper.read(3L);
		vo.setReply("수정된 댓글 내용");
		log.info(replyMapper.update(vo));
	}
	
	@Test
	public void testList(){
		replyMapper.getList(327683L, 0L).forEach(log::info);
	}
}
