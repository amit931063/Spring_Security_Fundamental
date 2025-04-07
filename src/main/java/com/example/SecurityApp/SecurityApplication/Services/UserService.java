package com.example.SecurityApp.SecurityApplication.Services;

import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Exceptions.ResourceNotFoundException;
import com.example.SecurityApp.SecurityApplication.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private  final UserRepository userRepository;
    private   final ModelMapper modelMapper;
    private  final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.passwordEncoder = passwordEncoder;
    }

    public  User getUserByEmail(String email) {
        return  userRepository.findByEmail(email)
                .orElse(null);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByEmail(username)
                .orElseThrow(()->new BadCredentialsException("user with this "+username +" not found"));
    }

     public  User  getUserById(Long userid) {
    return userRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("user with this " + userid + " not found"));
}
    public UserDTO SignUp(SignUpDTO signUpDTO) {
        Optional<User>user=userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()){
            throw  new BadCredentialsException(" user this email is already present " + signUpDTO.getEmail());
        }

        User tocreatedUser =modelMapper.map(signUpDTO,User.class);
        tocreatedUser.setPassword(passwordEncoder.encode(tocreatedUser.getPassword()));
        User savedUser=userRepository.save(tocreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

//    public  String LogIn(LoginDTO loginDTO) {
//      Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
//User user=(User) authentication.getPrincipal();
// return jwtService.generateToken(user);
//    }

}
