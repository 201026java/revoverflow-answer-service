package com.revature.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.models.Question;

@FeignClient(name = "RevOverflow-QuestionService")
@RequestMapping("/question")
public interface QuestionClient {

	@GetMapping("/non-paged/filter")
	public List<Question> getAllQuestionsByFilter(@RequestParam(value="questionType") String questionType, @RequestParam(value="location") String location, @RequestParam(value="id") int id);
	
}
