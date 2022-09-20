package com.mypenbox.mpb.registration.passwordReset;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public void savePasswordResetToken(PasswordResetToken token) {
        passwordResetTokenRepository.save(token);
    }

    public Optional<PasswordResetToken> getToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    public int setWasUsed(String token) {
        return passwordResetTokenRepository.updateWasUsed(token);
    }
}
