package co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.poetrypainting.domain.BoardVo;
import co.poetrypainting.domain.Criteria;

public interface BoardMapper {
	//목록조회
	List<BoardVo> getList();
	List<BoardVo> getListWithPaging(Criteria criteria);
//	int getListCount(int total);
	
	//글 등록
	void insert(BoardVo vo);
	
	//글 등록
	void insertSelectKey(BoardVo vo);
	
	//조회
	BoardVo read(Long bno);
	
	//삭제
	int delete(Long bno);
	
	//수정
	int update(BoardVo vo);
	int getTotalCnt(Criteria criteria);
	
	//댓글 갯수 반영
	void updateReplyCnt(@Param("bno") Long bno,@Param("amount") int amount);
	}
