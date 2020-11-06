package com.revature.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Question {

	private int id;
	private Integer acceptedId;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private LocalDateTime editDate;
	private boolean status;
	private int userID;

}
