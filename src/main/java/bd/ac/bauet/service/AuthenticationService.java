package bd.ac.bauet.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bd.ac.bauet.model.Role;
import bd.ac.bauet.model.User;
import bd.ac.bauet.repository.UserRepository;
import bd.ac.bauet.request.AuthenticationRequest;
import bd.ac.bauet.request.RegisterRequest;
import bd.ac.bauet.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JWTService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .userId(request.getUserId())
                                .name(request.getName())
                                .email(request.getEmail())
                                .phone(request.getPhone())
                                .gender(request.getGender())
                                .bloodGroup(request.getBloodGroup())
                                .birthday(request.getBirthday())
                                .batch(request.getBatch())
                                .graduationDate(request.getGraduationDate())
                                .jobTitle(request.getJobTitle())
                                .jobDescription(request.getJobDescription())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.USER)
                                .build();
                userRepository.save(user);
                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

}
