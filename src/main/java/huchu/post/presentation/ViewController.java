package huchu.post.presentation;

import huchu.post.application.PostServiceFacade;
import huchu.post.dto.PostResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ViewController {

    private final PostServiceFacade postServiceFacade;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<PostResponse> posts = postServiceFacade.readAll();
        model.addAttribute("posts", posts);
        return "gallery";
    }
}
