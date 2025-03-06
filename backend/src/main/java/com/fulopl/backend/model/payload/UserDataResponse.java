package com.fulopl.backend.model.payload;

import java.util.Set;

public record UserDataResponse(String email, Set<String> roles) {
}
