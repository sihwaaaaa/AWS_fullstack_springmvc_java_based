package co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.poetrypainting.domain.MemberVo;
import co.poetrypainting.domain.NoteVo;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoteMapperTests {
	
	@Autowired
	private NoteMapper noteMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
//	public void testInsert2(){
//		List<MemberVo> members = memberMapper.getList();
//		int i = 1;
//		for (MemberVo vo : members) {
//			for (MemberVo vo2 : members) {
//				NoteVo noteVo = new NoteVo();
//				noteVo.setSender(vo.getId());
//				noteVo.setReceiver(vo2.getId());
//				noteVo.setMessage("mapper 테스트 발송 :: " + i++);
//				noteMapper.insert(noteVo);
//			}
//		}
//	}
	
	@Test
	public void testInsert(){
		NoteVo vo = new NoteVo();
		vo.setSender("id1");
		vo.setReceiver("id3");
		vo.setMessage("mapper 테스트 발송");
		noteMapper.insert(vo);
		log.info(vo);
	}
	
	@Test
	public void testSelectOne(){
		log.info(noteMapper.selectOne(29L));
	}
	
	@Test
	public void testUpdate(){
		
		log.info(noteMapper.update(29L));
	}
	
	@Test
	public void testDelete(){
		noteMapper.delete(28L);
	}
	
	@Test
	public void testSendList(){
		noteMapper.sendList("id1").forEach(log::info);
	}
	@Test
	public void testReceiveList(){
		log.info(noteMapper.receiveList("id3"));
	}
	@Test
	public void testReceiveUncheckedList(){
		log.info(noteMapper.receiveUncheckedList("id3"));
	}
}
