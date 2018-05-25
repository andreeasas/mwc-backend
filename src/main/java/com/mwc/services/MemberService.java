package com.mwc.services;

import java.util.List;
import com.mwc.domain.Member;

public interface MemberService {
	List<Member> getAllByUser(Long userId);
	
	Member getById(Long id);
	
	Member saveOrUpdate(Member member);

    void delete(Long id);
}
