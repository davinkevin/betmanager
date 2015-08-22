package com.github.davinkevin.betmanager.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by kevin on 11/08/15 for betmanager
 */
public enum Result {
    ONE, N, TWO, NONE;

    @JsonCreator
    public static Result of(String value) {
        return Result.valueOf(value);
    }
}
