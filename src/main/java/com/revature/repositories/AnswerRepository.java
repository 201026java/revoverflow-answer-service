package com.revature.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.models.Answer;	

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

	/** @author Natasha Poser */
	Page<Answer> getAnswerByQuestionId(Pageable pageable, int questionId);
	
	/**@author ken */
	Page<Answer> getAllAnswersByUserId(Pageable pageable, int id);
	
	/** @author Natasha Poser */
	/*
	 * @Query("FROM Question q INNER JOIN Answer a ON q.acceptedId = a.id WHERE q.acceptedId = :acceptedId"
	 * ) Page<Answer> getAcceptedAnswerByQuestionId(Pageable pageable, int
	 * acceptedId);
	 */
	
} 
