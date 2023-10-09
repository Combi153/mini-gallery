package huchu.post.application;

import com.amazonaws.services.s3.model.ObjectMetadata;
import huchu.file.application.S3Service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PostUploader {

    private final S3Service s3Service;
    private final String location;

    public PostUploader(
            S3Service s3Service,
            @Value("${cloud.aws.s3.object.post}") String location
    ) {
        this.s3Service = s3Service;
        this.location = location;
    }

    public String upload(String fileName, MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            return s3Service.upload(location + fileName, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
