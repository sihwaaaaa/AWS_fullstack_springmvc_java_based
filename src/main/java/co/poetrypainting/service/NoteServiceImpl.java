package co.poetrypainting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.poetrypainting.domain.NoteVo;
import co.poetrypainting.mapper.NoteMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
	
	private NoteMapper noteMapper;
	
	@Override
	public int send(NoteVo vo) {
		
		return noteMapper.insert(vo);
	}

	@Override
	public NoteVo get(Long noteno) {
		
		return noteMapper.selectOne(noteno);
	}

	@Override
	public int receive(Long noteno) {
		
		return noteMapper.update(noteno);
	}

	@Override
	public int remove(Long noteno) {
		
		return noteMapper.delete(noteno);
	}

	@Override
	public List<NoteVo> getSendList(String id) {
		
		return noteMapper.sendList(id);
	}

	@Override
	public List<NoteVo> getReceiveList(String id) {
		
		return noteMapper.receiveList(id);
	}

	@Override
	public List<NoteVo> getReceiveUncheckedList(String id) {
		
		return noteMapper.receiveUncheckedList(id);
	}
	
}
