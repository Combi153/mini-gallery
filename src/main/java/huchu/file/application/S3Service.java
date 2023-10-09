package huchu.file.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service {

    private static final String SLASH = "/";

    private final AmazonS3 amazonS3;
    private final String bucket;
    private final String base;
    private final String domain;

    public S3Service(
            AmazonS3 amazonS3,
            @Value("${cloud.aws.s3.bucket}") String bucket,
            @Value("${cloud.aws.s3.object.base}") String base,
            @Value("${cloud.aws.s3.object.domain}") String domain
    ) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
        this.base = base;
        this.domain = domain;
    }

    public String upload(String fileName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        String location = base + fileName;

        amazonS3.putObject(bucket, location, file.getInputStream(), metadata);
        return getImageUrl(amazonS3.getUrl(bucket, location).toString());
    }

    private String getImageUrl(String s3Url) {
        int fileNameStart = s3Url.lastIndexOf(SLASH);
        return domain + SLASH + base + s3Url.substring(fileNameStart + 1);
    }
}
