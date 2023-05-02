package co.poetrypainting.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.poetrypainting.controller.UploadController;
import co.poetrypainting.domain.AttachFileDto;
import co.poetrypainting.domain.AttachVo;
import co.poetrypainting.domain.BoardVo;
import co.poetrypainting.domain.Criteria;
import co.poetrypainting.mapper.AttachMapper;
import co.poetrypainting.mapper.AttachMapperTests;
import co.poetrypainting.mapper.BoardMapper;
import co.poetrypainting.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
@ToString
@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardMapper;
	private ReplyMapper replyMapper;
	private AttachMapper attachMapper;
	
	@Override
	public void register(BoardVo vo) {
		// TODO Auto-generated method stub
		boardMapper.insertSelectKey(vo);
		Long bno = vo.getBno();
		List<AttachVo> list = vo.getAttachs();
		int idx = 0;
		if(list == null || list.size() == 0){
			return;
		}
		for (AttachVo attach : list) {
			attach.setBno(bno);
			attach.setOdr(idx++);
			attachMapper.insert(attach);
		}
	}

	@Override
	public BoardVo get(Long bno) {
		// TODO Auto-generated method stub
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVo vo) {
		attachMapper.deleteAll(vo.getBno());
		
		//수정 될 리스트
		List<AttachVo> list = vo.getAttachs();
		int idx = 0;
		for (AttachVo attach : list) {
			attach.setBno(vo.getBno());
			attach.setOdr(idx++);
			attachMapper.insert(attach);
		}
	
		return boardMapper.update(vo) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		replyMapper.deleteByBno(bno);
		attachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVo> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getListWithPaging(cri);
	}
	@Override
	public int getTotalCnt(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCnt(cri);
	}

	@Override
	public String deleteFile(AttachFileDto dto) {
	log.info(dto);
		
		String s = UploadController.getPATH() + "/" + dto.getPath() + "/" + dto.getUuid() + "_" + dto.getName();
		
		File file = new File(s);
		file.delete();
		if(dto.isImage()){
			s = UploadController.getPATH() + "/" + dto.getPath() + "/s_" + dto.getUuid() + "_" + dto.getName();
			file = new File(s);
			file.delete();
					
		}
		return dto.getUuid();
	}
	
}
