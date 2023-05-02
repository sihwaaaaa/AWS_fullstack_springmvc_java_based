package co.poetrypainting.domain;

import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	
	private String type;//t c w tc cw tw tcw
	private String keyword;
	
	private int getOffset(){
		return (pageNum -1) * amount;
	}
	
	public String getQueryString(){
		return UriComponentsBuilder.fromPath("")
//				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.build().toUriString();
	}
	public String getFullQueryString(){
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.build().toUriString();
	}
	
	public String[] getTypeArr(){
		return type == null ? new String[]{} : type.split("");
	}
	public Criteria(int pageNum, int amount){
		this.amount = amount;
		this.pageNum = pageNum;
	}
	
}
