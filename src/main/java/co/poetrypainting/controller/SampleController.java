package co.poetrypainting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.poetrypainting.domain.SampleVo;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("sample")
@Log4j
/**
 * 
 * controller
 * 요청과 응답 처리 제어
 * 사용자가 요청 -> 서버는 제공자
 * 
 * request : 1. URL (port이후 mapping 경로처리) 2. 파라미터수집(이전페이지값을 다음페이지에서 알려고 쿼리스트링으로 파라미터수집 get, post는 responseBody)
 * 3.HTTP Method (GET, POST, PUT, DELETE) .. attr, session, cookie
 * 
 * response : 1. MIME(Content-Type) ex)JSP cf. forward, viewResolver 2. header(text/html, application/json, text/xml, application/octet-stream)
 * 
 */
public class SampleController {
	@GetMapping(value="getText", produces="text/plain; charset=utf-8")
	public String getText(){
		return "안녕하세요";
	}
	@GetMapping("getSample")
	public SampleVo getSample(){
		return new SampleVo(112,"스타","로드");
	}
	@GetMapping("getList")
	public List<SampleVo> getList(){
		return IntStream.rangeClosed(1, 10).mapToObj(i -> new SampleVo(i, "first" + i , "last" + i)).collect(Collectors.toList());
	}
	@GetMapping("getMap")
	public Map<String, SampleVo> getMap(){
		Map<String, SampleVo> map = new HashMap<String, SampleVo>();
		map.put("first", new SampleVo(111, "그루트", "주니어"));
		return map;
	}
	@GetMapping(value="check",params={"height","weight"})
	public ResponseEntity<SampleVo> check(double height, double weight){
		SampleVo vo = new SampleVo(0, String.valueOf(height),String.valueOf(weight));
		ResponseEntity<SampleVo> result = null;
		
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
			
		}else{
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;
	}
	@GetMapping("product/{cat}/{pid}")
	public String[] getPath(
		@PathVariable("cat") String cat,
		@PathVariable("pid") String pid){
		return new String[] { "category: " + cat, "productid: " + pid };
	
	}
	@PostMapping("sample")
	public SampleVo convert(@RequestBody SampleVo sampleVo){
		log.warn(sampleVo);
		return sampleVo;
	}
}
