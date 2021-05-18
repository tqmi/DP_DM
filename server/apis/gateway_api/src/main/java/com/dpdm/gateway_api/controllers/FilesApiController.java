package com.dpdm.gateway_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-05-18T19:45:07.825591900+03:00[Europe/Bucharest]")
@Controller
@RequestMapping("${openapi.gateway.base-path:}")
public class FilesApiController implements FilesApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public FilesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
