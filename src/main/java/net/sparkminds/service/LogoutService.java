package net.sparkminds.service;

public interface LogoutService {
    String logout(String jwt);
    
    boolean checkJwtExistedRedis(String jwt);
}
