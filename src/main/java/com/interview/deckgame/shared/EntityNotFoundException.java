package com.interview.deckgame.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.experimental.StandardException;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class EntityNotFoundException extends RuntimeException {
}
