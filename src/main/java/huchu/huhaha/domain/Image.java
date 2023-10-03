package huchu.huhaha.domain;

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
public class Image {

    @Column(name = "image_id")
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageUrl;

    public Image(Long id, String imageName, String imageUrl) {
        this.id = id;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public Image(String imageName, String imageUrl) {
        this(null, imageName, imageUrl);
    }
}
