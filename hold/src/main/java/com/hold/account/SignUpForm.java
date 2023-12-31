package com.hold.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpForm {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 100)
    private String password;

    @NotBlank
    @Length(max = 20)
    private String name;

    @NotBlank
    @Pattern(regexp = "[0-9]{11}")
    private String phoneNum;
}
