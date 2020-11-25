package com.smallB.QOS.global.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@NoArgsConstructor
public class CreateRandomStrUtil {


    //랜덤 문자열 생성
    public String CreateRandomStr(int length) {
        String randomStr = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int point = random.nextInt(3);
            if(point == 0){
                randomStr += (char)((random.nextInt(26)) + 65);
            }
            else if(point == 1) {
                randomStr += (char)((random.nextInt(26)) + 97);
            }
            else {
                randomStr += (char)((random.nextInt(10)) + 48);
            }
        }
        return randomStr;
    }
}
