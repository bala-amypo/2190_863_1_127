import java.time.LocalDateTime;

private Integer priorityScore;

private LocalDateTime createdAt;

@PrePersist
public void onCreate() {
    this.createdAt = LocalDateTime.now();
}
