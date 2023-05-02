package co.poetrypainting.mapper;

import java.util.List;

import co.poetrypainting.domain.AttachVo;

public interface AttachMapper {
	void insert(AttachVo vo);
	
	void delete(String uuid);
	
	List<AttachVo> findBy(Long bno);
	
	void deleteAll(Long bno);
	
	List<AttachVo> getOldFiles();
}
