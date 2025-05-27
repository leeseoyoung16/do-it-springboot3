package com.mysite.sbb;

import com.mysite.sbb.answer.AnswerService;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJPA() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용 없음";
			this.questionService.create(subject, content,null);
		}
	}

	@Test
	void testAnswer()
	{
		// ID가 308인 질문 객체를 가져옴
		Question question = this.questionRepository.findById(308)
				.orElseThrow(() -> new RuntimeException("질문 없음"));

		for (int i = 1; i <= 30; i++) {
			String content = String.format("테스트 답변 입니다.:[%03d]", i);
			this.answerService.create(question, content, null);
		}
	}
}
