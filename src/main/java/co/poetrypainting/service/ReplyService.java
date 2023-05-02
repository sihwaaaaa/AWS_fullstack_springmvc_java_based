package co.poetrypainting.service;

import java.util.List;

import co.poetrypainting.domain.ReplyVo;

public interface ReplyService {
	int register(ReplyVo vo);
	
	ReplyVo get(Long rno);
	
	int remove(Long rno);
	int modify(ReplyVo vo);

	List<ReplyVo> getList(Long bno, Long rno);

}
