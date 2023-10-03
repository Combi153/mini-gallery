package huchu.huhaha.presentation;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import huchu.huhaha.application.ImageFacadeService;
import huchu.huhaha.dto.ImageResponse;
import huchu.huhaha.dto.ImageSaveRequest;
import huchu.huhaha.dto.ImageSaveResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageFacadeService imageFacadeService;

    @PostMapping(value = "/images", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageSaveResponse> save(
            @RequestPart(value = "dto") ImageSaveRequest imageSaveRequest,
            @RequestPart(value = "file") MultipartFile file
    ) {
        ImageSaveResponse response = imageFacadeService.save(imageSaveRequest, file);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/images")
    public List<ImageResponse> readAll() {
        return imageFacadeService.readAll();
    }
}
