package com.friendApp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * Created by sivareddy.guduru on 1/2/18.
 */
public  class EmailValidation {

        private static Logger logger = LoggerFactory.getLogger(EmailValidation.class);

        private final static String EMAIL_PATTERN = "^[a-z0-9._-]+@[a-z0-9-]+(?:\\.[a-z0-9-]+)*$";

        public static void validate(String... emails) throws FriendApiException {
            for (String email : emails) {
                if(StringUtils.isEmpty(email)) {
                    logger.error("Email Address is empty");
                    throw new FriendApiException(HttpStatus.BAD_REQUEST, "Email Address is empty");
                }

                if(email.length() > 100) {
                    logger.error("Email Address is more than 100 characters");
                    throw new FriendApiException(HttpStatus.BAD_REQUEST, "Email Address is more than 100 characters");
                }

                if(!email.matches(EMAIL_PATTERN)) {
                    logger.error("Email Address is not correct format");
                    throw new FriendApiException(HttpStatus.BAD_REQUEST,"Email Address is not correct format");
                }
            }
        }


}
