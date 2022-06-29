package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String hashMD5withSalt(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        md.update(salt);
        byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    public boolean checkPassword(User user){
        User u = userRepository.findByLogin(user.getLogin());
        if(u == null){
            return false;
        }

        String newPass = hashMD5withSalt(user.getPassword(), u.getSalt());
        return newPass.equals(u.getPassword());
    }

    @GetMapping("/")
    public String login(Model pModel){
        pModel.addAttribute("action", "login");
        return "connexion";
    }

    @GetMapping("/register")
    public String register(Model pModel){
        pModel.addAttribute("action", "register");
        return "connexion";
    }

    @PostMapping("/login-confirm")
    public String connexion(@ModelAttribute User user, HttpSession session, RedirectAttributes redir){
        if (checkPassword(user)){
            session.setAttribute("infoConnexion", user);
            return "redirect:/index";
        } else {
            redir.addFlashAttribute("msg", "Identifiant ou mot de passe incorrect ");
            return "redirect:/";
        }

    }

    @PostMapping("/register-confirm")
    public String enregistrer(@ModelAttribute User user, RedirectAttributes redir){
        User u = userRepository.findByLogin(user.getLogin());
        if(u != null){
            redir.addFlashAttribute("msg", "Cet identifiant éxiste déjà.");
            return "redirect:/register";
        }
        try {
            byte[] salt= getSalt();
            user.setSalt(salt);
            user.setPassword(hashMD5withSalt(user.getPassword(), user.getSalt()));
            userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }
}
