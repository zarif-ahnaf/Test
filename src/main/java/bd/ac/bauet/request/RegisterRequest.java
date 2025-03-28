package bd.ac.bauet.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userId;

    private String name;
    private String email;
    private String phone;
    private String gender;
    private String bloodGroup;
    private LocalDate birthday;

    private String password;
    private String batch;
    private LocalDate graduationDate;

    private String jobTitle;
    private String jobDescription;

    private String linkedIn;
    private String facebook;
    private String github;  

}
