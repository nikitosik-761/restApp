package restApplication.rest.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import restApplication.rest.models.User;
import restApplication.rest.repositories.UserDerailsRepo;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserDerailsRepo userDerailsRepo;

    @Autowired
    public MyAuthenticationSuccessHandler(UserDerailsRepo userDerailsRepo) {
        this.userDerailsRepo = userDerailsRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        String id = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String userpic = oauth2User.getAttribute("picture");
        String gender = oauth2User.getAttribute("gender");
        String locale = oauth2User.getAttribute("locale");

        User user = userDerailsRepo.findById(id).orElseGet(() -> {
            User newUser =new User();

            newUser.setId(id);
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setUserpic(userpic);
            newUser.setGender(gender);
            newUser.setLocale(locale);

            return newUser;
                });

        user.setLastVisit(LocalDateTime.now());

        userDerailsRepo.save(user);

        response.sendRedirect("/");
    }
}
