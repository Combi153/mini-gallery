package huchu.post.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post {

    @Column(name = "post_id")
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String postName;

    @Column(nullable = false)
    private String postImageUrl;

    public Post(Long id, String postName, String postImageUrl) {
        this.id = id;
        this.postName = postName;
        this.postImageUrl = postImageUrl;
    }

    public Post(String postName, String postImageUrl) {
        this(null, postName, postImageUrl);
    }
}
