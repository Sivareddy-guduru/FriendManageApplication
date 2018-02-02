package com.friendApp.utils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;


/**
 * Created by sivareddy.guduru on 31/1/18.
 */
public class RecipientsRequest {

        @NotNull
        @Size(max = 100)
        @Pattern(regexp = "^[a-z0-9._-]+@[a-z0-9-]+(?:\\.[a-z0-9-]+)*$")
        private String sender;

        @NotNull
        private String text;

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

}
