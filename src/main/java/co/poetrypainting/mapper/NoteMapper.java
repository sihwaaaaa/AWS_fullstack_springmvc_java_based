package co.poetrypainting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import co.poetrypainting.domain.NoteVo;

public interface NoteMapper {
	// CRUD

	// 노트 작성
	@Insert("INSERT INTO TBL_NOTE (noteno, sender, receiver, message) values(seq_note.nextval, #{sender}, #{receiver}, #{message})")
	int insert(NoteVo vo);
	
	// 단일 조회
	@Select("SELECT * FROM TBL_NOTE where noteno = #{noteno}")
	NoteVo selectOne(Long noteno);
	
	// 수신 확인
	@Update("update tbl_note set rdate = sysdate where noteno = #{noteno}")
	int update(Long noteno);
	
	// 노트 삭제
	@Delete("delete from tbl_note where noteno = #{noteno}")
	int delete(Long noteno);
	
	// 보낸거
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 AND SENDER = #{sender} ORDER BY 1 DESC")
	List<NoteVo> sendList(String sender);
	
	//받은거
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 AND RECEIVER  = #{receiver} ORDER BY 1 DESC")
	List<NoteVo> receiveList(String receiver);
	
	//받았는데 확인 안한거
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 AND RECEIVER  = #{receiver} and rdate is null ORDER BY 1 DESC")
	List<NoteVo> receiveUncheckedList(String receiver);
	
}
