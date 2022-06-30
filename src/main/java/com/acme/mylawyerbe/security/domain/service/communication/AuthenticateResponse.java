package com.acme.mylawyerbe.security.domain.service.communication;

import com.acme.mylawyerbe.security.resource.AuthenticateResource;
import com.acme.mylawyerbe.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}