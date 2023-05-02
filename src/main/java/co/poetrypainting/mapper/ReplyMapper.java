package co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import co.poetrypainting.domain.ReplyVo;
import lombok.AllArgsConstructor;

public interface ReplyMapper {

	int insert(ReplyVo vo);
	
	ReplyVo read(Long rno);
	
	List<ReplyVo> getList(@Param("bno") Long bno, @Param("rno") Long rno);
	
	int update(ReplyVo vo);

	int delete(Long rno);
	int deleteByBno(Long bno);
}
