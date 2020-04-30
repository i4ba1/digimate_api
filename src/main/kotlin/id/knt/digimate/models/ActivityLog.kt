package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.time.LocalDateTime;
import java.util.*
import javax.persistence.*

@Entity(name = "ActivityLog")
@Table(name = "activity_log")
data class ActivityLog(
		@Id
		@GeneratedValue(generator = "UUID")
		@GenericGenerator(
				name = "UUID",
				strategy = "org.hibernate.id.UUIDGenerator"
		)
		@Column(name = "id", updatable = false, nullable = false)
		var id: UUID? = null,

		@ManyToOne
		@JoinColumn(name = "user_id", nullable = false)
		var user: User? = null,

		@ManyToOne
		@JoinColumn(name = "media_id", nullable = false)
		var media: Media? = null,

		@ManyToOne
		@JoinColumn(name = "article_id", nullable = false)
		var article: Article? = null,

		@ManyToOne
		@JoinColumn(name = "running_text_id", nullable = false)
		var runningText: RunningText? = null,

		@Temporal(TemporalType.TIMESTAMP)
		var activityDate: Date? = null,

		@Column(name = "activity", columnDefinition = "char(50)")
		var activity: String? = "",

		@Column(name = "log", columnDefinition = "text")
		var errorLog: Exception? = null
)