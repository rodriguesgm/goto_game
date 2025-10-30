package com.interview.deckgame.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@StandardException
public class InvalidOperationException extends RuntimeException {
}
