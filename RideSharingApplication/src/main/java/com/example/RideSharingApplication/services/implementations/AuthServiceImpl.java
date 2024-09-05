package com.example.RideSharingApplication.services.implementations;


import com.example.RideSharingApplication.dto.DriverDto;
import com.example.RideSharingApplication.dto.SignupDto;
import com.example.RideSharingApplication.dto.UserDto;
import com.example.RideSharingApplication.entities.User;
import com.example.RideSharingApplication.entities.enums.Role;
import com.example.RideSharingApplication.exceptions.RuntimeConflictException;
import com.example.RideSharingApplication.repositories.UserRepository;
import com.example.RideSharingApplication.services.AuthService;
import com.example.RideSharingApplication.services.RiderService;
import com.example.RideSharingApplication.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
//        TODO add wallet related service here
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
