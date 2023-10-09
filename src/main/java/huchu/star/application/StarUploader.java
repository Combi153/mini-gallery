package huchu.star.application;

import huchu.file.application.S3Service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StarUploader {

    private static final String ROUTE_IMAGE_FORMAT = "png";
    private final S3Service s3Service;
    private final String directory;

    public StarUploader(
            S3Service s3Service,
            @Value("${cloud.aws.s3.object.star}") String directory
    ) {
        this.s3Service = s3Service;
        this.directory = directory;
    }

    public String upload(BufferedImage bufferedImage) {
        String fileName = UUID.randomUUID() + "." + ROUTE_IMAGE_FORMAT;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, ROUTE_IMAGE_FORMAT, byteArrayOutputStream);
            return s3Service.upload(directory, fileName, new ImageMultipartFile(byteArrayOutputStream, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
