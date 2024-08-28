package com.colombia.eps.verify.domain.spi;

import java.util.Map;

public interface ISqsPersistencePort {
    Map<String,String> sendMessage(String telephone, String queueUrl);
}
