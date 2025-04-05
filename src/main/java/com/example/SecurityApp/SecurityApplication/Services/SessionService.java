package com.example.SecurityApp.SecurityApplication.Services;

import com.example.SecurityApp.SecurityApplication.Entities.Session;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private  final int  SESSION_LIMIT = 2;
    public void generateNewSession( User user , String refreshToken){
        List<Session>userSessions=sessionRepository.findByUser(user);

        if(userSessions.size()== SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);

        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);


    }
    public void validateSession( String refreshToken){
        Session session= sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException(" Session not found for this " + refreshToken ));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }


}
