@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedUser");

        model.addAttribute("user", user);

        return "home";
    }
}