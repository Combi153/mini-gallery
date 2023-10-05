package huchu.huhaha.presentation;

import huchu.huhaha.application.ImageServiceFacade;
import huchu.huhaha.dto.ImageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final ImageServiceFacade imageServiceFacade;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<ImageResponse> images = imageServiceFacade.readAll();
        model.addAttribute("images", images);
        return "gallery";
    }
}
