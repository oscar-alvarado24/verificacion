package com.colombia.eps.verify.domain.api;

import java.util.Map;

public interface ISqsServicePort {
    Map<String,String> sendMessageQueue(String telephone, String queueUrl);
}
