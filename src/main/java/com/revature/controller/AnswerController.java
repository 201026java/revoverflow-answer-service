package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.messaging.MessageEvent;
import com.revature.messaging.MessageService;
import com.revature.models.Answer;
import com.revature.services.AnswerService;

@RestController
@RequestMapping("/answer")

public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	MessageService messageService;
	
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
		messageService.triggerEvent(new MessageEvent(answer));
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
	
	/** get all the answers by filter data
	 * @param questionType = defines the question type (Revature or Location)
	 * @param location = specific location if questionType is Location
	 * @param id = the id of the user, or 0 if not specified
	 * @return
	 */
	@GetMapping("/filter")
	public Page<Answer> getAllAnswersByFilter(Pageable pageable, @RequestParam String questionType, @RequestParam String location, @RequestParam int id){	
		return answerService.getAllAnswersByFilter(pageable, questionType, location, id);
	}

}
 