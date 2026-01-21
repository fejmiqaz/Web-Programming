package mk.ukim.finki.wp.jan2024g2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Post {

    public Post() {
    }

    public Post(String title, String content, LocalDate dateCreated, PostType postType, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.postType = postType;
        this.tags = tags;
        this.likes = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate dateCreated;

    private PostType postType;

    @ManyToMany
    private List<Tag> tags;

    private Integer likes = 0;
}
