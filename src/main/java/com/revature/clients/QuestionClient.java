package com.revature.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.models.Question;

@FeignClient(name = "RevOverflow-QuestionService", url = "${environment.rss.question.url}")
public interface QuestionClient {

	/** @Author Mark Alsip
	 * Fetches filtered questions from the questions service.
	 * Since openfeign doesn't like page or pageable a new endpoint
	 * just for this operation was made in question controller.
	 * 
	 * @return note that it returns a list, not a page.
	 */
	@GetMapping("/question/non-paged/filter")
	public List<Question> getAllQuestionsByFilter(@RequestParam(value="questionType") String questionType, @RequestParam(value="location") String location, @RequestParam(value="id") int id);
	
}
