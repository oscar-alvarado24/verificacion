package com.colombia.eps.verify.infrastructure.input.rest;

import com.colombia.eps.verify.application.dto.CodeRequest;
import com.colombia.eps.verify.application.dto.EmailRequest;
import com.colombia.eps.verify.application.dto.RequestResponse;
import com.colombia.eps.verify.application.handler.IVerifyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyController {
    private final IVerifyHandler verifyHandler;

    @Operation(summary = "send code to patient for your verify")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Generate and send code process successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Fail process for generate and send code", content = @Content)
    })
    @PostMapping("/generate")
    public RequestResponse<String> generateCode(@RequestBody EmailRequest email){
        return verifyHandler.sendCode(email);
    }
    @Operation(summary = "send code to patient for your verify")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " code process successfully", content = @Content),
            @ApiResponse(responseCode = "500", description = "Fail process for validate code", content = @Content)
    })
    @PostMapping("/validate")
    public RequestResponse<String> validateCode(@RequestBody CodeRequest codeRequest){
        return verifyHandler.validateCode(codeRequest);
    }
}