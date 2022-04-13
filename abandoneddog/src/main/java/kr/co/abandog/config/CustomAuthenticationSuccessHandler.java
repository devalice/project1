package kr.co.abandog.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private static int TIME = 60 * 60; // 1시간
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
    									HttpServletResponse response, 
    									Authentication authentication) throws IOException, ServletException {
    	
    	RequestCache requestCache = new HttpSessionRequestCache();

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    	
    	//세션 타임 아웃
    	request.getSession().setMaxInactiveInterval(TIME);
    	
    	// IP, 세션 ID
    	WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();

    	Date today = new Date();
    	SimpleDateFormat  date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
    	
    	log.info("[로그인 시간: "+date.format(today)+" | 로그인 IP: "+web.getRemoteAddress()+" | 로그인 사용자:"+ authentication.getName()+"]");

    	//로그인 성공 시 서칭하던 페이지로 이동
        setDefaultTargetUrl("/");
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            //인증 받기 전 url로 이동
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,targetUrl);
        }else{
            //기본 url로 가도록 함함
           redirectStrategy.sendRedirect(request,response,getDefaultTargetUrl());
        }
    }
   
}
