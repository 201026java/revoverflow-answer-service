package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.clients.QuestionClient;
import com.revature.models.Answer;
import com.revature.models.Question;
import com.revature.models.User;
import com.revature.repositories.AnswerRepository;

@Service
public class AnswerService {
	
	@Autowired
 	AnswerRepository answerRepository;
 
	@Autowired
	QuestionClient questionClient;
 
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
	
	/** @Author Mark Alsip
	 * This monster was born out of separating the tables to different services,
	 * which means there is no relational database management for our services.
	 * So I made this, which pull the table data from questions that is filtered
	 * by specifications not included in the answer table, and the data pulled 
	 * from the answer table which pulls all answers by a user.
	 * 
	 * After fetching, using for loops I manually create a join table and save it in a list,
	 * which is THEN converted to a page. Like magic.
	 * 
	 * @return even though I use Lists a lot for this method, it does return a page with the filtered data
	 */
	public Page<Answer> getAllAnswersByFilter(Pageable pageable, String questionType, String location, int id) {
		List<Question> filteredQuestions = questionClient.getAllQuestionsByFilter(questionType, location, 0);
		List<Answer> filteredAnswers = answerRepository.getAllNonPagedAnswersByUserId(id);
		
		List<Answer> finalFilter = new ArrayList<Answer>();
		for (Answer a : filteredAnswers) {
			for (Question q : filteredQuestions) {
				if(a.getQuestionId() == q.getId()) {
					finalFilter.add(a);
					break;
				}
			}
		}
		
		Page<Answer> filtered = new PageImpl<>(finalFilter, pageable, finalFilter.size());
		
		return filtered;
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


