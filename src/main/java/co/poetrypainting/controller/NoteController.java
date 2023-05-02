package co.poetrypainting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.poetrypainting.domain.NoteVo;
import co.poetrypainting.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("note")
@AllArgsConstructor
public class NoteController {
	
	private NoteService service;
	
	@PostMapping("new")
	public int send(@RequestBody NoteVo vo) {//리퀘스트 바디는 js에서 파라미터 받기위해
		log.info(vo);
		return service.send(vo);
	}
	@GetMapping("{noteno}")
	public NoteVo getNote(@PathVariable Long noteno){
		return service.get(noteno);
	}
	@PutMapping("{noteno}")
	public int receive(@PathVariable Long noteno){
		return service.receive(noteno);
	}
	@DeleteMapping("{noteno}")
	public int remove(@PathVariable Long noteno){
		return service.remove(noteno);
	}
	
	@GetMapping("s/{id}")
	public List<NoteVo> getSendList(@PathVariable String id){
		log.info(id);
		return service.getSendList(id);
	}
	@GetMapping("r/{id}")
	public List<NoteVo> getReceiveList(@PathVariable String id){
		log.info(id);
		return service.getReceiveList(id);
	}
	@GetMapping("r/c/{id}")
	public List<NoteVo> getReceiveUncheckedList(@PathVariable String id){
		log.info(id);
		return service.getReceiveUncheckedList(id);
	}
}
