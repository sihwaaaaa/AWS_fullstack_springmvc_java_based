package co.poetrypainting.mapper;

import co.poetrypainting.domain.MemberVo;

public interface MemberMapper {
	MemberVo read(String userid);
}
