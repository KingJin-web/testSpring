package com.ex.res.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)//在生成的json字符串中排除空属性
@Data
public class JsonModel implements Serializable {
    private static final long serialVersionUID = 6083159741448953894L;
    private Integer code;
    private String msg;
    private Object obj;
    private String url;

}
