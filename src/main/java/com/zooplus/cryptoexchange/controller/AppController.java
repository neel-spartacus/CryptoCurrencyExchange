package com.zooplus.cryptoexchange.controller;


import com.zooplus.cryptoexchange.authentication.UserAgent;
import com.zooplus.cryptoexchange.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;

import static com.zooplus.cryptoexchange.utils.CurrencyUtils.currencies;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AppController {
    public static final String DEFAULT_SOUCE = "USD";
    

    @Autowired
    private CryptoDataService cryptoDataService;


    @Autowired private CryptoRateAgent exchangeAgent;


    @Autowired
    private UserAgent userAgent;

    
    @RequestMapping(value="/index", method=GET)
    public String home(final Model model) {
        model.addAttribute("currencies", currencies());
        model.addAttribute("searchs",    cryptoDataService.lastQueries());
        model.addAttribute("error",      null);
        return "index"; 
    }
    
    @RequestMapping(value="/rate", method=GET)
    public String rate(@RequestParam("target") final String target,@RequestParam("clientIp") String clientIP,
                       final Model model) {
        if(clientIP.isEmpty()){
            InetAddress ipAddress=InetAddress.getLoopbackAddress();
            clientIP=ipAddress.getHostAddress();
        }
        GetExchangeRateResponse response = exchangeAgent.get(target,clientIP);
        
        model.addAttribute("currencies", currencies());
        model.addAttribute("searchs",    response.getLatestSearches());
        model.addAttribute("rate",       response.getCurrent());
        model.addAttribute("error",      response.getIssue().getMessage());
        return "index"; 
    }

    @RequestMapping(value="/signin", method=GET)
    public String login(@RequestParam(value="error", required=false) String error,
                        @RequestParam(value="logout",required=false) String logout,
                            final Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials provided.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out from Challenge successfully.");
        }
        return "signin";
    }

    @RequestMapping(value="/trySignin", method=POST)
    public String signIn(@RequestParam(value="username", required=false) String username,
                        @RequestParam(value="password",required=false) String password,
                        final Model model){

        return userAgent.signIn(username,password)?home(model):"signin";
    }

    @RequestMapping(value="/signup", method=POST)
    public String signup(@RequestParam(value="firstname",  required=false) String firstname,
                         @RequestParam(value="lastname",   required=false) String lastname,
                         @RequestParam(value="email",      required=false) String email,
                         @RequestParam(value="bday",       required=false) String bday,
                         @RequestParam(value="password",   required=false) String password,
                         @RequestParam(value="repassword", required=false) String repassword,
                         final Model model) {
        return userAgent.signup(firstname, lastname, email, bday, password)
               ? home(model) 
               : "signin";
    }
}
