package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        final Long memberId = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findOne(memberId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        final Long memberId1 = memberService.join(member1);
        final Long memberId2 = memberService.join(member2);

        //then
        fail("중복 회원으로 예외가 발생해야한다");
    }

}