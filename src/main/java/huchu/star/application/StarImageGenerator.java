package huchu.star.application;

import java.awt.image.BufferedImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StarImageGenerator {

    private final StarUploader starUploader;

    public String generate() {
        StarDrawer starDrawer = StarDrawer.from(800);
        BufferedImage bufferedImage = starDrawer.bufferedImage();

        //upload
        String imageUrl = starUploader.upload(bufferedImage);
        starDrawer.dispose();

        return imageUrl;
    }
}
