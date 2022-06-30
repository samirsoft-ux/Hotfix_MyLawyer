package com.acme.mylawyerbe.security.domain.service.communication;

import com.acme.mylawyerbe.security.resource.UserResource;
import com.acme.mylawyerbe.shared.domain.service.communication.BaseResponse;

public class RegisterResponse extends BaseResponse<UserResource> {
    public RegisterResponse(String message) {
        super(message);
    }

    public RegisterResponse(UserResource resource) {
        super(resource);
    }
}