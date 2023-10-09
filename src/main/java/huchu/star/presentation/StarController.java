package huchu.star.presentation;

import static org.springframework.http.HttpStatus.CREATED;

import huchu.star.application.StarImageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StarController {

    private final StarImageGenerator starImageGenerator;

    @PostMapping("/stars")
    public ResponseEntity<StarSaveResponse> save() {
        String imageUrl = starImageGenerator.generate();
        return ResponseEntity.status(CREATED).body(new StarSaveResponse(imageUrl));
    }
}
