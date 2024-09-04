package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.*;



public class Task {
    static private int count = 0;
    private final int id;
    private String description;

    @Override
    public String toString() {
        return id +
                "createdAt=" + createdAt +
                " description='" + description + '\'' +
                " state=" + state +
                " updatedAt=" + updatedAt;
    }

    private Status state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    final private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updatedAt;

    public Task(String description) {
        count += 1;
        this.description = description;
        this.id = count;
        this.state = Status.in_progress;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task() {
        this.id = count;
        this.createdAt = LocalDateTime.now();
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {

        return id;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
