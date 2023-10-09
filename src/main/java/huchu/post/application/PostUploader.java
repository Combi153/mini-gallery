package huchu.post.application;

import huchu.file.application.S3Service;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PostUploader {

    private final S3Service s3Service;
    private final String directory;

    public PostUploader(
            S3Service s3Service,
            @Value("${cloud.aws.s3.object.post}") String directory
    ) {
        this.s3Service = s3Service;
        this.directory = directory;
    }

    public String upload(String fileName, MultipartFile file) {
        try {
            return s3Service.upload(directory, fileName, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
