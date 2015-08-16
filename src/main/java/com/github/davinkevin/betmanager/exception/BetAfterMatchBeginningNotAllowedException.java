package com.github.davinkevin.betmanager.exception;

/**
 * Created by kevin on 16/08/15 for betmanager
 */
public class BetAfterMatchBeginningNotAllowedException extends RuntimeException{
    final String code;

    public BetAfterMatchBeginningNotAllowedException() {
        this.code = "You can't bet after match beginning";
    }
}
