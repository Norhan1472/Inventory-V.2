package com.spring.products.controller;

import com.spring.products.Email.EmailSender;
import com.spring.products.dto.JwtLogin;
import com.spring.products.dto.JwtResponse;
import com.spring.products.entity.User;
import com.spring.products.entity.VerificationToken;
import com.spring.products.repository.RoleRepo;
import com.spring.products.repository.UserRepo;
import com.spring.products.service.VerificationTokenService;
import com.spring.products.service.implementation.TokenService;
import com.spring.products.service.implementation.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("api/user/v1")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    TokenService tokenService;
    PasswordEncoder password;
    UserService userService;
    RoleRepo roleRepo;
    UserRepo userRepo;
    VerificationTokenService verificationTokenService;
    EmailSender emailSender;
    //api/user/v1/signIn
    @PostMapping("signIn")
    public JwtResponse signIn(@RequestBody JwtLogin jwtLogin){
       return tokenService.login(jwtLogin);
    }
    //api/user/v1/signUp
    @PostMapping("signUp")
    public String signUp(@RequestBody JwtLogin jwtLogin){
        User user = new User();
        user.setEmail(jwtLogin.getEmail());
        user.setPassword(password.encode(jwtLogin.getPassword()));
        //System.out.println(user.getPassword());
        user.setActive(true);
        user.getRoles().add(roleRepo.findAll().get(0));
        User userSaved = userService.signUp(user);
        System.out.println(userSaved.getPassword());
    //send email
    String token = generateToken(userSaved);
    String link = "http://localhost:8080/api/user/v1/confirm?token=" + token;
        emailSender.send(
                userSaved.getEmail(),
    buildEmail(userSaved.getFirstName(), link));


        return "Please Verify Your Account";
     }
    public String generateToken(User userSaved){
        String token = UUID.randomUUID().toString();
        verificationTokenService.saveVerificationToken(token,userSaved);
        System.out.println(token);
        return token;
    }



    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    //api/user/v1/signUpAdmin
    @PostMapping("signUpAdmin")
    public String signUpAdmin(@RequestBody JwtLogin jwtLogin){
        User user = new User();
        user.setEmail(jwtLogin.getEmail());
        System.out.println(password.encode(jwtLogin.getPassword()));
        user.setPassword(password.encode(jwtLogin.getPassword()));
        user.setActive(true);
        user.getRoles().add(roleRepo.findAll().get(1));
        userService.signUp(user);
        return user.getPassword();
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return confirmToken(token);
    }
    public String confirmToken(String token) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if(Objects.isNull(verificationToken)){
            return "INVALID TOKEN";
        }else{
            User user = verificationToken.getUser();
            if(!user.isActive()){
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                if (verificationToken.getExpiryDate().before(currentTime)){
                    return "Verification Token Has Expired";
                }else{
                    user.setActive(true);
                    userRepo.save(user);
                    return "Your Account Is Successfully Activated";
                }
            }else{
                return "YOUR ACCOUNT ALREADY ACTIVATED";
            }
        }
    }

}
