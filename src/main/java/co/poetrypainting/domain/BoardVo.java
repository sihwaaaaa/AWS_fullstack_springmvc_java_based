package co.poetrypainting.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Alias("board")
@NoArgsConstructor
public class BoardVo {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate; 
	private Date updatedate;
	
	private int replycnt;
	private List<AttachVo> attachs = new ArrayList<>();
}
