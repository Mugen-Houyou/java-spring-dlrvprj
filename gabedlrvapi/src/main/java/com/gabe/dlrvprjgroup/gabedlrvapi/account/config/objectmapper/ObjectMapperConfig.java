package com.gabe.dlrvprjgroup.gabedlrvapi.account.config.objectmapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    // Spring Boot 서버가 실행될 때, ObjectMapper에 대한 bean이 정의가 안되어있다면, 자기가 직접 기본값으로 생성함
    // 아래는 기본값이 아니라 커스텀하여 생성하는 것.
    // 특정 오브젝트뿐만 아니라 이 서버 전체에 설정함.
    @Bean
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module()); // JDK 8 버전 이후로 나온 클래스들을 처리하기 위해서. `Optional` 등.
        objectMapper.registerModule(new JavaTimeModule()); // LocalDate 등.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); // 알 수 없는 컬럼(JSON 필드)에 대해서는 무시하고 파싱할 것.
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false); // Serialization 시 빈 bean을 생성할 수 있음을 명시.

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Serialization 시 날짜 관련.
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy()); // Snake case로 네이밍

        return objectMapper;

    }


}





















