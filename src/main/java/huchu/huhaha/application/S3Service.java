package huchu.huhaha.application;

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
    private final String cloudFrontDomain;

    public S3Service(
            AmazonS3 amazonS3,
            @Value("${cloud.aws.s3.bucket}") String bucket,
            @Value("${cloud.aws.cloudFront.domain}") String cloudFrontDomain
    ) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
        this.cloudFrontDomain = cloudFrontDomain;
    }

    public String upload(String fileName, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
            return getImageUrl(amazonS3.getUrl(bucket, fileName).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getImageUrl(String s3Url) {
        int fileNameStart = s3Url.lastIndexOf(SLASH);
        return cloudFrontDomain + s3Url.substring(fileNameStart);
    }
}
