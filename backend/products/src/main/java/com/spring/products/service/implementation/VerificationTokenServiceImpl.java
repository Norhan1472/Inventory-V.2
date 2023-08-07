package com.spring.products.service.implementation;
import com.spring.products.entity.User;
import com.spring.products.entity.VerificationToken;

import com.spring.products.repository.VerificationTokenRepo;
import com.spring.products.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private VerificationTokenRepo verificationTokenRepo;
    @Override
    public VerificationToken findByToken(String token){
        return verificationTokenRepo.findByToken(token);
    }
    @Override
    public VerificationToken findByUser(User user){
        return verificationTokenRepo.findByUser(user);
    }
    @Override
    public void saveVerificationToken(String token,User user){
        System.out.println(user.getEmail());
        System.out.println(token);
        System.out.println("Hello");
        VerificationToken verificationToken = new VerificationToken(token,user);
        verificationToken.setExpiryDate(calculateExpiryDate(15));
        verificationTokenRepo.save(verificationToken);
    }
   public Timestamp calculateExpiryDate(int expiryDateInMinutes){
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.MINUTE,expiryDateInMinutes);
       return new Timestamp(calendar.getTime().getTime());
   }
}
