package com.mwc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Member;
import com.mwc.repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> getAllByUserId(long userId) {
        return memberRepository.findAllByUserId(userId);
	}

	@Override
	public Member getById(long id) {
		return memberRepository.findOne(id);
	}

	@Override
	public Member saveOrUpdate(Member member) {
		memberRepository.save(member);
        return member;
	}

	@Override
	public void delete(long id) {
		memberRepository.delete(id);
	}
	
}
