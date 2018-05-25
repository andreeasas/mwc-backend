package com.mwc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mwc.domain.Member;
import com.mwc.repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberRepository memberRepository;
	
	@Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

	@Override
	public List<Member> getAllByUser(Long userId) {
		List<Member> members = new ArrayList<>();
		List<Member> members2 = memberRepository.findByUserId(userId);
		
		members = members2;
//        memberRepository.findByUserId(userId).forEach(members::add); //fun with Java 8
        return members;
	}

	@Override
	public Member getById(Long id) {
		return memberRepository.findOne(id);
	}

	@Override
	public Member saveOrUpdate(Member member) {
		memberRepository.save(member);
        return member;
	}

	@Override
	public void delete(Long id) {
		memberRepository.delete(id);
		
	}
	
	
}
