package org.llanesagudelo.myapp.weeklyEntry;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class WeeklyEntry {

    @Id
    private UUID id;

    private String title;
    private String content;

    public WeeklyEntry() {
    }

    public WeeklyEntry(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
