package com.plitsoft.ojt.global.interceptor;

import com.plitsoft.ojt.global.dto.CommonResponseDTO;
import com.plitsoft.ojt.global.exception.InvalidRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class VersionInterceptControl {
    public boolean preInterceptor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clientVersion = getClientVersion(request);
        String apiVersion = getRequestAPIVersion(request);

        CommonResponseDTO.VersionResponseDTO versionResponse = new CommonResponseDTO.VersionResponseDTO(clientVersion, apiVersion);
        request.setAttribute("version", versionResponse);
        return true;
    }

    private static String getRequestAPIVersion(HttpServletRequest request) throws InvalidRequestException {
        String[] urlCheck = request.getContextPath().split("/");
        if (urlCheck.length < 2) {
            throw new InvalidRequestException();
        }

        return urlCheck[1];
    }

    private String getClientVersion(HttpServletRequest request) throws InvalidRequestException {
        String clientVersion = request.getHeader("Client-Version");
        if (clientVersion == null) {
            throw new InvalidRequestException();
        }

        return clientVersion;
    }

}
