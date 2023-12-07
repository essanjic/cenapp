package com.group6.cenapp.service;

import com.group6.cenapp.exception.RegisterErrorException;
import com.group6.cenapp.exception.ResourceNotFoundException;
import com.group6.cenapp.model.dto.UserInfoDTO;
import com.group6.cenapp.model.entity.Restaurant;
import com.group6.cenapp.model.entity.UserInfo;
import com.group6.cenapp.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(email);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }

    public UserInfo findByEmail(String email) throws ResourceNotFoundException {
        Optional<UserInfo> entityOptional = repository.findByEmail(email);
        return entityOptional.orElseThrow(() -> new ResourceNotFoundException("Entity not found with email: " + email));
    }
    public UserInfo getUserInfo(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByEmail(email);
        return userInfo.orElseThrow(() -> new UsernameNotFoundException("email not found " + email));
    }


    public String addUser(UserInfo userInfo) throws RegisterErrorException {
        Optional<UserInfo> existingUserByEmail = repository.findByEmail(userInfo.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new RegisterErrorException("Email already exists.");
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }

    public UserInfo updateUserInfo(UserInfo userInfo) throws ResourceNotFoundException {
        Optional<UserInfo> foundUser = repository.findByEmail(userInfo.getEmail());
        if (foundUser.isPresent()) {
            foundUser.get().setName(userInfo.getName());
            foundUser.get().setLast_name(userInfo.getLast_name());
            return userInfo;
        }else {
            throw new ResourceNotFoundException(userInfo.getName() +  " " + userInfo.getLast_name() + " not found");
        }
    }

    public String switchUserRole(Integer id) {
        Optional<UserInfo> userDetail = repository.findById(id);
        if (userDetail.isPresent()) {
            UserInfo userInfo = userDetail.get();
            String currentRole = userInfo.getRoles();

            if ("ROLE_USER".equals(currentRole)) {
                userInfo.setRoles("ROLE_ADMIN");
            } else if ("ROLE_ADMIN".equals(currentRole)) {
                userInfo.setRoles("ROLE_USER");
            }
            repository.save(userInfo);
            return "User role switched successfully";
        } else {
            throw new UsernameNotFoundException("User not found " + id);
        }
    }
    public List<UserInfoDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserInfoDTO convertToDto(UserInfo user) {
        return modelMapper.map(user, UserInfoDTO.class);
    }


    public boolean existsRootUser() {
        return repository.existsByRoles("ROLE_ROOT");
    }

    public boolean isEmailAvailable(String email) {
        return repository.isEmailAvailable(email);
    }


    public List<Integer> addFavourite(Integer idUser, Integer idRest) throws ResourceNotFoundException {
        Optional<UserInfo> user = repository.findById(idUser);
        if (user.isPresent()) {
            user.get().getFavourites().add(idRest);
            return user.get().getFavourites();
        }else{
            throw new ResourceNotFoundException(idUser + " not found");
        }
    }
    public List<Integer> getFavourites(Integer idUser) throws ResourceNotFoundException {
        Optional<UserInfo> userOptional = repository.findById(idUser);
        if (userOptional.isPresent()) {
            UserInfo user = userOptional.get();
            List<Integer> favourites = user.getFavourites();
            return favourites != null ? favourites : Collections.emptyList();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }


    public List<Restaurant> getRestFavs(Integer idUser) throws ResourceNotFoundException {
        List<Integer> favourites = this.getFavourites(idUser);
        if (favourites != null) {
            List<Restaurant> restaurantList = new ArrayList<>();
            for (Integer fav : favourites) {
                restaurantList.add(restaurantService.getRestaurantById(fav).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found")));
            }
            return restaurantList;
        } else {
            throw new ResourceNotFoundException("Favourites not found");
        }
    }
    public List<Integer> toggleFav(Integer idUser, Integer idRest) throws ResourceNotFoundException {
        Optional<UserInfo> userOptional = repository.findById(idUser);
        if (userOptional.isPresent()) {
            UserInfo user = userOptional.get();
            List<Integer> userFavs = user.getFavourites();
            if (userFavs == null) {
                userFavs = new ArrayList<>();
            }
            if (userFavs.contains(idRest)) {
                userFavs.remove(idRest);
            } else {
                userFavs.add(idRest);
            }
            user.setFavourites(userFavs);
            repository.save(user); // Guardar cambios en la base de datos
            return userFavs;
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }



    public List<Integer> deleteFav(Integer idUser, Integer idRest) throws ResourceNotFoundException {
        Optional<UserInfo> user = repository.findById(idUser);
        List<Integer> favs;
        if (user.isPresent()) {
             favs = user.get().getFavourites();
            favs.removeIf(fav -> fav.equals(idRest));
        }else{
            throw new ResourceNotFoundException("User not found");
        }
        return favs;
    }





}