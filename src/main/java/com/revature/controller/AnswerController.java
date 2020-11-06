package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Answer;
import com.revature.services.AnswerService;

@RestController
@RequestMapping("/answers")
@CrossOrigin(
		origins = { "http://localhost:8500" }, 
		methods = { RequestMethod.GET, RequestMethod.PUT, 
					RequestMethod.PATCH, RequestMethod.POST },
		allowedHeaders = { "content-type" }
	)
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
/** @Author Natasha Poser 
 * 	@return This is the GetAnswers end-point. It retrieves all Answers in the database */
	@GetMapping
	public Page<Answer>getAnswers(Pageable pageable){
			return answerService.getAnswers(pageable);
	}

	/** @author Natasha Poser 
	 * @param questionId = question_id
	 * @return This is the GetAnswerByQuestionId end-point. It retrieves all answers associated with a specific Question ID */
	@GetMapping("/{questionId}") 
	public Page<Answer> getAnswersByQuestionId(Pageable pageable, @PathVariable int questionId){
		return answerService.getAnswerByQuestionId(pageable, questionId);
	}
	

	/** @Author James Walls */
	/** Adds new answers and updates existing ones. */
	@PostMapping
	public Answer saveAnswer( @RequestBody Answer answer) {
		return answerService.save(answer);
	}
	
	/**@author ken*/
	/**@param id = the id of the user
	 * get all the Answers by the user id */
	@GetMapping("/user/{id}")
	public Page<Answer> getAllAnswersByUserID(Pageable pageable,@PathVariable int id){
		return answerService.getAllAnswersByUserID(pageable, id);
	} 
	
	/** @author Natasha Poser 
	 * @param acceptedId = accepted_id
	 * @return This is the GetAcceptedAnswerByQuestionId end-point. */
	/*
	 * @GetMapping("/acceptedAnswers/{acceptedId}") public Page<Answer>
	 * getAcceptedAnswerByQuestionId(Pageable pageable, @PathVariable int
	 * acceptedId){ return answerService.getAcceptedAnswerByQuestionId(pageable,
	 * acceptedId); }
	 */
	
	/** @author Natasha Poser 
	 * @param id = id
	 * @return This is the GetAnswerById end-point. It retrieves the answer by it's own unique ID*/
	@GetMapping("/id/{id}")
	public Answer getAnswerById(@PathVariable int id){
		return answerService.getAnswerById(id);
	}
}
 