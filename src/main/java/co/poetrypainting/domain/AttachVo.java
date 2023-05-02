package co.poetrypainting.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
@Alias("attach")
public class AttachVo extends AttachFileDto{
	private Long bno;
	
}
