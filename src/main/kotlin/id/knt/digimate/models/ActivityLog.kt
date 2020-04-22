package id.knt.digimate.models

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

data class ActivityLog(
   @ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	val user: User,
	
	@ManyToOne
	@JoinColumn(name="media_id", nullable = false)
	val media: Media,
	
	@ManyToOne
	@JoinColumn(name="article_id", nullable = false)
	val article: Article,
	
	@ManyToOne
	@JoinColumn(name="running_text_id", nullable = false)
	val runningText: RunningText,
	
	@Temporal(TemporalType.TIMESTAMP)
	val activityDate: LocalDateTime,
	
	@Column(name = "activity", columnDefinition = "char(50)")
	val activity: String,

	@Column(name = "log", columnDefinition = "text")
	val log: String
)