package co.poetrypainting.service;

import java.util.List;

import co.poetrypainting.domain.AttachFileDto;
import co.poetrypainting.domain.BoardVo;
import co.poetrypainting.domain.Criteria;

public interface BoardService {
	void register(BoardVo vo);
	
	BoardVo get(Long bno);
	
	boolean modify(BoardVo vo);
	
	boolean remove(Long bno);
	
	List<BoardVo> getList(Criteria cri);
	
	int getTotalCnt(Criteria cri);
	
	String deleteFile(AttachFileDto dto);
}
