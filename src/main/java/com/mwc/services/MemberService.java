package com.mwc.services;

import java.util.List;
import com.mwc.domain.Member;

public interface MemberService {
	
	List<Member> getAllByUserId(long userId);
	
	Member getById(long id);
	
	Member saveOrUpdate(Member member);

    void delete(long id);
}
