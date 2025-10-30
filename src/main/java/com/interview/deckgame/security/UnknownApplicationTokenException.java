package com.interview.deckgame.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "invalid application token")
@StandardException
public class UnknownApplicationTokenException extends RuntimeException {
}
