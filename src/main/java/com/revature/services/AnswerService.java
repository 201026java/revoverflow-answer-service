package com.revature.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.models.Answer;
import com.revature.models.User;
import com.revature.repositories.AnswerRepository;

@Service
public class AnswerService {
	
 @Autowired
 AnswerRepository answerRepository;
 
  /** @Author Natasha Poser 
   * @return retrieves all answers matching a specific question ID*/
 public Page<Answer> getAnswerByQuestionId(Pageable pageable, int questionId){
	 return answerRepository.getAnswerByQuestionId(pageable, questionId);
 }
 
	
	/** @Author James Walls */
 	/** Adds new answers and updates existing ones. */
	public Answer save(Answer answer) {
		return answerRepository.save(answer);
	} 

	/**@author ken*/
	public Page<Answer> getAllAnswersByUserID(Pageable pageable, int id){
		return answerRepository.getAllAnswersByUserId(pageable, id);		
	}

	/** @author Natasha Poser 
	 * @return retrieves all answers in database*/
	 public Page<Answer> getAnswers(Pageable pageable) {
		 return answerRepository.findAll(pageable);
	 }
	
	/** @author Natasha Poser 
	 * @return retrieves the accepted answers accosiated with a specific Question Id*/ 
	/*
	 * public Page<Answer> getAcceptedAnswerByQuestionId(Pageable pageable, int
	 * acceptedId){ return answerRepository.getAcceptedAnswerByQuestionId(pageable,
	 * acceptedId); }
	 */
	
	/** @author Natasha Poser 
	 * @return retrieves a specific Answer by using it's ID*/
	public Answer getAnswerById(int id) {
		return answerRepository.findById(id)
				// If no answer is found by the particular ID then HTTP Status is given 
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}
	
	public Collection<GrantedAuthority> getAuthority(User user){
		Collection<GrantedAuthority>auths = new ArrayList<>();
		SimpleGrantedAuthority a= null; 
		
			if(user.isAdmin()) {
				a = new SimpleGrantedAuthority("ADMIN");
				auths.add(a);
				a = new SimpleGrantedAuthority("USER");
				auths.add(a);
			}else {
				a = new SimpleGrantedAuthority("USER");
				auths.add(a);
				
			}
		
		return auths;
 }
	
}


