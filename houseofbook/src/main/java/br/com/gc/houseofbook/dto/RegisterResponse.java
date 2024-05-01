package br.com.gc.houseofbook.dto;

import br.com.gc.houseofbook.entities.User;

public record RegisterResponse(Long id, String username) {

    public static RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(user.getId(), user.getName());
    }
}
