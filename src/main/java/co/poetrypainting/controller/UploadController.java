package co.poetrypainting.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.poetrypainting.domain.AttachFileDto;
import co.poetrypainting.service.BoardService;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.tasks.UnsupportedFormatException;

@Controller
@Log4j
public class UploadController {
	@Getter
	private static final String PATH = "d:/upload";

	@Autowired
	private BoardService boardService;

	@GetMapping("upload")
	public void upload() {
	}

	@GetMapping("uploadAjax")
	public void uploadAjax() {

	}

	@PostMapping(value = "uploadAjax", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public List<AttachFileDto> uploadAjax(MultipartFile[] files) {
		List<AttachFileDto> list = new ArrayList<>();
		File uploadPath = new File(PATH, getFolder());
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		for (MultipartFile m : files) {
			// 실제 스트림 전송

			String uuidStr = UUID.randomUUID().toString();
			String tName = uuidStr + "_" + m.getOriginalFilename();
			File f = new File(uploadPath, tName);
			boolean image = false;

			try {
				image = isImage(f);
				m.transferTo(f);

				if (isImage(f)) {
					// 섬네일 처리
					File f2 = new File(uploadPath, "s_" + tName);
					Thumbnails.of(f).crop(Positions.CENTER).size(200, 200).toFile(f2);
				}

			} catch (UnsupportedFormatException e) {
				// TODO: handle exception
				image = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			AttachFileDto dto = new AttachFileDto();
			dto.setUuid(uuidStr);
			dto.setPath(getFolder());
			dto.setImage(image);
			dto.setName(m.getOriginalFilename());
			list.add(dto);

		}
		return list;
	}

	@GetMapping("deleteFile")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public String deleteFile(AttachFileDto dto) {
		return boardService.deleteFile(dto);
	}

	@GetMapping("display")
	@ResponseBody
	public ResponseEntity<byte[]> display(AttachFileDto dto, Boolean thumb) {// 소문자면
																				// 파라미터
																				// 무조건
																				// 있어야하기때문에
																				// 매퍼클래스
																				// 사용
		// fileName : path + uuid + name
		String s = PATH + "/" + dto.getPath() + "/" + (thumb != null && thumb ? "s_" : "") + dto.getUuid() + "_"
				+ dto.getName();
		;
		File file = new File(s);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);// 에러
																										// 없애주껭
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping("download")
	@ResponseBody
	public ResponseEntity<byte[]> download(AttachFileDto dto) {// 소문자면 파라미터 무조건
																// 있어야하기때문에
																// 매퍼클래스 사용
		// fileName : path + uuid + name
		String s = PATH + "/" + dto.getPath() + "/" + dto.getUuid() + "_" + dto.getName();
		;
		File file = new File(s);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(dto.getName().getBytes("utf-8"), "iso-8859-1"));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);// 에러
																										// 없애주껭
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@GetMapping("list")
	@ResponseBody // 응답 타입제어 //모든게 responseBody로 가야할 시 그때 restController
	public List<String> test() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		return list;
	}

	@GetMapping(value = "dto", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody // 이게 언급되어있으면 jsp로 안나옴 이데이터의 순수타입이 나옴
	public AttachFileDto getDto() {
		AttachFileDto dto = new AttachFileDto();
		dto.setImage(true);
		dto.setName("파일명.jsp");
		return dto;
	}

	private String getFolder() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	}

	// file -> mime확인
	private boolean isImage(File file) throws IOException {
		List<String> excludes = Arrays.asList("ico", "webp");
		int idx = file.toString().lastIndexOf(".");

		if (idx == -1) {
			return false;
		}
		String ext = file.toString().substring(idx + 1);
		if (excludes.contains(ext)) {
			return false;
		}

		String mime = Files.probeContentType(file.toPath());

		return mime != null && mime.startsWith("image");
	}

	@PostMapping("/ckImage")@ResponseBody
	public Map<String, Object> ckImage(MultipartHttpServletRequest request) throws IllegalStateException, IOException{
		log.info(request);
		MultipartFile multipartFile = request.getFile("upload");
		String origin = multipartFile.getOriginalFilename();
		
		
			
			String uuidStr = UUID.randomUUID().toString();
			String tName = uuidStr + "_" + origin;
					
			File f = new File(PATH, tName);
			
			AttachFileDto dto = new AttachFileDto();
			dto.setUuid(uuidStr);
			dto.setPath("");
			dto.setName(origin);
			
			multipartFile.transferTo(f);
			
			Map<String, Object> map = new HashMap<>();
			map.put("uploaded", true);
			
			map.put("url", "/display" + dto.getUrl());
			
			log.info(map);
			return map;
	}
}
