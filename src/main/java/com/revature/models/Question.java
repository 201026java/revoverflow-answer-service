package com.revature.models;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Question {

	private int id;
	private Integer acceptedId;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private LocalDateTime editDate;
	private boolean status;
	private int userID;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptedId == null) ? 0 : acceptedId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + id;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (acceptedId == null) {
			if (other.acceptedId != null)
				return false;
		} else if (!acceptedId.equals(other.acceptedId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (editDate == null) {
			if (other.editDate != null)
				return false;
		} else if (!editDate.equals(other.editDate))
			return false;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	public Question(int id, Integer acceptedId, @NotBlank(message = "Title requires a string value") String title,
			@NotBlank(message = "Content requires a string value") String content, LocalDateTime creationDate,
			LocalDateTime editDate, boolean status, int userID) {
		super();
		this.id = id;
		this.acceptedId = acceptedId;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.editDate = editDate;
		this.status = status;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", acceptedId=" + acceptedId + ", title=" + title + ", content=" + content
				+ ", creationDate=" + creationDate + ", editDate=" + editDate + ", status=" + status + ", userID="
				+ userID + "]";
	}

}
