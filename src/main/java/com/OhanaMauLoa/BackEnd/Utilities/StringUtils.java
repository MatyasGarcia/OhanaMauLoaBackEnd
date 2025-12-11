package com.OhanaMauLoa.BackEnd.Utilities;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {
  public boolean isEmpty(String textoValidate){
    return textoValidate == null || textoValidate.isBlank();
  }
}
