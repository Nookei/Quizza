package com.wvs.quizza.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "username not unique")
public class UsernameNotUniqueException extends RuntimeException {

}
