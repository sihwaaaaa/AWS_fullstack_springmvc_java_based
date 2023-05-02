package co.poetrypainting.service;

import java.util.List;

import co.poetrypainting.domain.NoteVo;

public interface NoteService {
		
	int send(NoteVo vo);
		
	NoteVo get(Long noteno);
		
	int receive(Long noteno);
		
	int remove(Long noteno);
		
	List<NoteVo> getSendList(String id);
		
	List<NoteVo> getReceiveList(String id);
		
	List<NoteVo> getReceiveUncheckedList(String id);
}
