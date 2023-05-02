package co.poetrypainting.handler;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import co.poetrypainting.domain.MemberVo;
import co.poetrypainting.domain.NoteVo;
import co.poetrypainting.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.security.crypto.core.DSARandomBitsSource;
@Log4j
// ServerSocket
@EnableWebSocket
public class NoteHandler extends TextWebSocketHandler{
	//접속자 관리 객체
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	@Autowired
	private NoteService noteService;
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session) throws Exception {//접속자 정보
//		sessions.add(session);
//		sessions.forEach(log::warn);
//		log.warn(sessions.stream().map(s -> getIdBySession(s)).collect(Collectors.toList()));
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		log.warn(session.getId() + "로그아웃");
//		sessions.remove(session);
//	}
//	
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String receiver = message.getPayload(); //js,ws.send() 수신자
//		
//		String sender = getIdBySession(session);
//		List<NoteVo> list0 = noteService.getSendList(sender);
//		List<NoteVo> list1 = noteService.getReceiveList(receiver);
//		List<NoteVo> list2 = noteService.getReceiveUncheckedList(receiver);
//		
//		
//		Map<String, Object> map= new HashMap<>();
//		map.put("sendList", list0);
//		map.put("receiveList", list1);
//		map.put("receiveUncheckedList", list2);
//		map.put("sender", sender);
//		
//		Gson gson = new Gson();
//		
//		for (WebSocketSession s : sessions) {
//			if(receiver.equals(getIdBySession(s)) || session == s){
//				s.sendMessage(new TextMessage(gson.toJson(map)));
//			}
//		}
//		// a > b
//	}
//	private String getIdBySession(WebSocketSession session){
//		Object obj = session.getAttributes().get("member");
//		String id = null;
//		if(obj != null && obj instanceof MemberVo){
//			id = ((MemberVo)obj).getId();
//		}
//		return id;
//	}
	
	//lifecycle
	//접속, 실제할일, 종료
}
