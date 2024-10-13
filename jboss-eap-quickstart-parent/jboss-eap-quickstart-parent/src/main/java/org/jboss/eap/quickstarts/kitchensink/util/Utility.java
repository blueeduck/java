package org.jboss.eap.quickstarts.kitchensink.util;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.eap.quickstarts.kitchensink.entity.MemberEntity;
import org.jboss.eap.quickstarts.kitchensink.model.Member;

public class Utility {

	public static List<Member> getMemberModels(List<MemberEntity> memberEntities)
	{
		return memberEntities!=null ? memberEntities.stream().map(e-> new Member(e.getId(), e.getName(), e.getEmail(), e.getPhoneNumber()))
		.collect(Collectors.toList()) : null;
	}
	
	public static Member getMemberModel(MemberEntity memberEntity)
	{
		return memberEntity!=null ? new Member(memberEntity.getId(), memberEntity.getName(), memberEntity.getEmail(), memberEntity.getPhoneNumber()) : null;
	}
	
	public static MemberEntity getMemberEntity(Member member)
	{
		return member!=null ? new MemberEntity(member.getId(), member.getName(), member.getEmail(), member.getPhoneNumber()) : null;
	}
}
