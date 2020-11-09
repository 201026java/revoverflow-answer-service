package com.revature.messaging;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.revature.services.AnswerService;

@Service
public class MessageService {
	
	private static Set<Integer> eventCache = new HashSet<>();
	
	@Autowired
	private AnswerService answerService;

	@Autowired
	private KafkaTemplate<String, MessageEvent> kt;
	
	public void triggerEvent(MessageEvent event) {
		eventCache.add(event.hashCode());
		
		kt.send("answer", event);
	}
	
	@KafkaListener(topics = "answer")
	public void processMessageEvent(MessageEvent event) {
		if(event.getAnswer() == null) {
			return;
		}
		if(eventCache.contains(event.hashCode())) {
			eventCache.remove(event.hashCode());
			return;
		}
			
		System.out.println("___________________________________________________");
		System.out.println("        I GOT A FISH");
		System.out.println(event);
		System.out.println("___________________________________________________");
		
		answerService.save(event.getAnswer());

	}
}
