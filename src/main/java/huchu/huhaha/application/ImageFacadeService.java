package huchu.huhaha.application;

import huchu.huhaha.dto.ImageResponse;
import huchu.huhaha.dto.ImageSaveRequest;
import huchu.huhaha.dto.ImageSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageFacadeService {

    private final S3Service s3Service;
    private final ImageService imageService;
    private final TransactionTemplate transactionTemplate;

    public ImageSaveResponse save(ImageSaveRequest imageSaveRequest, MultipartFile file) {
        String fileName = imageSaveRequest.fileName();
        String uploadedUrl = s3Service.upload(fileName, file);
        return transactionTemplate.execute(status -> imageService.save(fileName, uploadedUrl));
    }

    public List<ImageResponse> readAll() {
        return imageService.readAll();
    }
}
