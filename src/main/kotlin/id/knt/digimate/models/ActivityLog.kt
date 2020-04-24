package id.knt.digimate.models

import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.time.LocalDateTime;
import java.util.*
import javax.persistence.*

@Entity(name = "ActivityLog")
@Table(name = "activity_log")
data class ActivityLog (
				@Id
				@GeneratedValue(generator = "UUID")
				@GenericGenerator(
								name = "UUID",
								strategy = "org.hibernate.id.UUIDGenerator"
				)
				@Column(name = "id", updatable = false, nullable = false)
				val id: UUID? = null,

				@ManyToOne
				@JoinColumn(name = "user_id", nullable = false)
				val user: User? = null,

				@ManyToOne
				@JoinColumn(name = "media_id", nullable = false)
				val media: Media? = null,

				@ManyToOne
				@JoinColumn(name = "article_id", nullable = false)
				val article: Article? = null,

				@ManyToOne
				@JoinColumn(name = "running_text_id", nullable = false)
				val runningText: RunningText? = null,

				@Temporal(TemporalType.TIMESTAMP)
				val activityDate: Date? = null,

				@Column(name = "activity", columnDefinition = "char(50)")
				val activity: String = "",

				@Column(name = "log", columnDefinition = "text")
				val log: String? = ""
)