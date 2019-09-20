package com.vironit.basumatarau.socialSsoApp.controller;

import com.vironit.basumatarau.socialSsoApp.exception.ResourceNotFoundException;
import com.vironit.basumatarau.socialSsoApp.model.User;
import com.vironit.basumatarau.socialSsoApp.repository.UserRepository;
import com.vironit.basumatarau.socialSsoApp.security.CurrentUser;
import com.vironit.basumatarau.socialSsoApp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public String getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>" +
                "<style>\n" +
                ".card {\n" +
                "  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);\n" +
                "  max-width: 300px;\n" +
                "  margin: auto;\n" +
                "  text-align: center;\n" +
                "  font-family: arial;\n" +
                "}\n" +
                "\n" +
                ".title {\n" +
                "  color: grey;\n" +
                "  font-size: 18px;\n" +
                "}\n" +
                "\n" +
                "button {\n" +
                "  border: none;\n" +
                "  outline: 0;\n" +
                "  display: inline-block;\n" +
                "  padding: 8px;\n" +
                "  color: white;\n" +
                "  background-color: #000;\n" +
                "  text-align: center;\n" +
                "  cursor: pointer;\n" +
                "  width: 100%;\n" +
                "  font-size: 18px;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "  text-decoration: none;\n" +
                "  font-size: 22px;\n" +
                "  color: black;\n" +
                "}\n" +
                "\n" +
                "button:hover, a:hover {\n" +
                "  opacity: 0.7;\n" +
                "}\n" +
                "</style>" +
                "</head>" +
                "</body>" +
                "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                "\n" +
                "<h2 style=\"text-align:center\">this is supposed to be a native app interface...</h2>" +
                "<div class=\"card\">\n" +
                "  <img src=\"" + user.getImageUrl() +"\" alt=\"" + user.getName() + "'s pic" + "\" style=\"width:100%\">\n" +
                "  <h1>" + user.getName() + "</h1>\n" +
                "  <p class=\"title\"> auth provider: " + user.getProvider() + "</p>\n" +
                "  <p>" + user.getEmail() + "</p>\n" +
                "</div>" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
