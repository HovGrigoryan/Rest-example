package am.itspace.restexample.endpoint;


import am.itspace.restexample.dto.AuthRequest;
import am.itspace.restexample.dto.AuthResponse;
import am.itspace.restexample.dto.UserDto;
import am.itspace.restexample.exception.DublicateEntityException;
import am.itspace.restexample.exception.ResourceNotFoundException;
import am.itspace.restexample.model.User;
import am.itspace.restexample.repository.UserRepository;
import am.itspace.restexample.service.SmsService;
import am.itspace.restexample.service.impl.TwilioSmsService;
import am.itspace.restexample.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenUtil tokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    @Qualifier("SMSCSmsService")
    private  SmsService smsService;

    @PostMapping("/user/auth")
    public ResponseEntity auth(@RequestBody AuthRequest authRequest) {
        Optional<User> byEmail = userRepository.findByEmail(authRequest.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(authRequest.getPassword(), byEmail.get().getPassword())) {
                String token = tokenUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(AuthResponse.builder()
                        .token(token)
                        .name(user.getName())
                        .surname(user.getSurname())
                        .build());
            }
        }



        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @PostMapping("/user")
    public User save(@RequestBody UserDto userDto) {
        if (!userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = modelMapper.map(userDto, User.class);


            User save = userRepository.save(user);
            if (userDto.getPhoneNumber() != null && !userDto.getPhoneNumber().isEmpty()) {
                smsService.send(userDto.getPhoneNumber(),"Hello " + userDto.getName());
            }
            return save;
        } else {
            throw new DublicateEntityException("username already exists");
        }

    }

    @PutMapping("/user/{userId}/image")

    public void uploadImage(@PathVariable("userId") int userId, @RequestParam("image") MultipartFile file) {
        Optional<User> byId = userRepository.findById(userId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException();
        }
        System.out.println(file.getOriginalFilename());
    }
}
