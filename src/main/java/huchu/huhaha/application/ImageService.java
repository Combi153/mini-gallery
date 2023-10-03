package huchu.huhaha.application;

import huchu.huhaha.domain.Image;
import huchu.huhaha.domain.ImageRepository;
import huchu.huhaha.dto.ImageResponse;
import huchu.huhaha.dto.ImageSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public ImageSaveResponse save(String fileName, String uploadedUrl) {
        Image image = imageRepository.save(new Image(fileName, uploadedUrl));
        return new ImageSaveResponse(image.id());
    }

    @Transactional(readOnly = true)
    public List<ImageResponse> readAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream()
                .map(ImageResponse::from)
                .toList();
    }
}
