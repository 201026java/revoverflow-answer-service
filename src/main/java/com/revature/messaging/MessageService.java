package com.revature.messaging;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.revature.repositories.AnswerRepository;


public class MessageService {
	
	private static Set<Integer> eventCache = new HashSet<>();
	
	@Autowired
	private AnswerRepository answerDao;

	@Autowired
	private KafkaTemplate<String, MessageEvent> kt;
	
	public void triggerAnswerEvent(MessageEvent event) {
		eventCache.add(event.hashCode());
		
		if(event.getOperation() == Operation.DELETE) {
			kt.send("answer", event);
		}
		
		kt.send("answer", event);
	}
	
	@KafkaListener(topics = "answer")
	public void processMessageEvent(MessageEvent event) {
		if(eventCache.contains(event.hashCode())) {
			eventCache.remove(event.hashCode());
			return;
		}
		
		//Flashcard card = event.getFlashcard();
		
		switch(event.getOperation()) {
		case CREATE:
			//this.answerDao.save(card);
			break;
		case UPDATE:
			//this.answerDao.save(card);
			break;
		case DELETE:
			//this.answerDao.deleteById(card.getId());
			break;
		}
	}
}
