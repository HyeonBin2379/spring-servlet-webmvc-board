package com.ssg.board.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {

    INSTANCE;

    private final ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);  // update 수행 시 VO의 필드 중 일부를 갱신하는 경우를 고려
    }

    public ModelMapper get() {
        return modelMapper;
    }
}
