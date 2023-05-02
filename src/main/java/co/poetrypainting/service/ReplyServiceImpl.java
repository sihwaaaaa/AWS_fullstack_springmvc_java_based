package co.poetrypainting.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.poetrypainting.domain.ReplyVo;
import co.poetrypainting.mapper.BoardMapper;
import co.poetrypainting.mapper.ReplyMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private BoardMapper boardMapper;
	private ReplyMapper replyMapper;
	
	
	@Override
	@Transactional
	public int register(ReplyVo vo) {
		// TODO Auto-generated method stub
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVo get(Long rno) {
		// TODO Auto-generated method stub
		return replyMapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	@Transactional
	public int modify(ReplyVo vo) {
		// TODO Auto-generated method stub
		return replyMapper.update(vo);
	}

	@Override
	public List<ReplyVo> getList(Long bno, Long rno) {
		return replyMapper.getList(bno, rno);
		// TODO Auto-generated method stub
	}
	

}
