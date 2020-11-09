package com.revature.messaging;

import com.revature.models.Answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Getter
public class MessageEvent {

	private Answer answer;
	
}
