package com.hold.account;

import com.hold.domain.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        if(errors.hasErrors()) {
            return "account/signup";
        }
        accountService.processNewAccount(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/checkEmailToken")
    public String checkEmailToken(String token, String email, Model model) {
        Account account = accountRepository.findByEmail(email); // 이메일 유무 확인
        String view = "account/checkedEmail";
        if(account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }
        if(!account.getEmailCheckToken().equals(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }

        account.setEmailVerified(true); // 이메일 인증 완료
        account.setJoinedAt(LocalDateTime.now());
        model.addAttribute("name", account.getName());
        return view;
    }


}
