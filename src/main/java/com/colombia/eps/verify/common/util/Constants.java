package com.colombia.eps.verify.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String STATUS = "status";
    public static final String CODE_200 = "200";
    public static final String CODE_500 = "500";
    public static final String BODY = "body";
    public static final String MESSAGE = "message";
    public static final String FAILED_COMMUNICATION = "Fallo de comunicacion por favor intenta mas tarde";
    public static final String ERROR_MESSAGE_SERVICE = "Fallo interno, por favor reportalo a la eps con el mensaje: ";
    public static final String NOT_GENERATE_SESSION_BODY = "Falló el proceso de generación de sesion";
    public static final String NOT_SEND_MESSAGE_BODY = "Fallo el proceso de envio del mensaje a la cola";
    public static final String SEND_MESSAGE_SUSESFULL = "Mensaje enviado a la cola satisfactoriamente";
    public static final String ROLE_SESSION_NAME_SQS = "sqs-util";
    public static final String PATIENT_EMAIL = "email";
    public static final String NAME_FILE_SCHEMA = "schemaGetNumberPatient";
    public static final String MESSAGE_OK = "code generated with the id: ";
    public static final String ERROR_MESSAGE = "An error occurred ";
    public static final String COLOMBIAN_INDICATIVE = "+57";
    public static final String VALIDATE_SUSESFULL = "Validacion exitosa";
    public static final String VALIDATE_FAILED = "Falló el proceso de validacion ";
    public static final String EMPTY = "";
    public static final String VERIFICATIONCHECK = "verificationcheck was not found";
    public static final String DONT_GENERATE_COME_MESSAGE = "Primero debe generar el codigo antes de validarlo";
    public static final String CODE_400 = "400";
    public static final String WRONG_CODE = "Codigo vencido o incorrecto, genera uno nuevo";
    public static final String EXCEPTION_NAME = "name";
}