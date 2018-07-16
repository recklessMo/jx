package com.recklessMo.jx.service;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.util.HashMap;
import java.util.Map;

public class ReadContentService {

    /**
     * 初始化函数，包括连接端口
     */
    public boolean init() throws Exception{
        boolean result = false;
        for(int i = 1001; i <= 1016; i++){
            int res = ReadApi.INSTANCE.CVR_InitComm(i);
            if(res == 1){
                result = true;
                break;
            }
        }

        Thread.sleep(300);
        int status = ReadApi.INSTANCE.CVR_Authenticate();
        if(status != 1){
            result = false;
        }
        return result;
    }


    /**
     * 读取信息
     */
    public Map<String, String> readContent(){
        Map<String, String> result = new HashMap<>();
        int res = ReadApi.INSTANCE.CVR_Read_Content(1);
        if(res == 1){
            Pointer data = new Memory(50);
            IntByReference length = new IntByReference();
            int name = ReadApi.INSTANCE.GetPeopleName(data, length);
            if(name == 1){
                result.put("name", data.getString(0, "gbk"));
            }
            data = new Memory(10);
            int sex = ReadApi.INSTANCE.GetPeopleSex(data, length);
            if(sex == 1){
                result.put("sex", data.getString(0, "gbk"));
            }
            data = new Memory(10);
            int nation = ReadApi.INSTANCE.GetPeopleNation(data, length);
            if(nation == 1){
                result.put("nation", data.getString(0, "gbk"));
            }
            data = new Memory(20);
            int birthDay = ReadApi.INSTANCE.GetPeopleBirthday(data, length);
            if(birthDay == 1){
                result.put("birthDay", data.getString(0, "gbk"));
            }
            data = new Memory(100);
            int address = ReadApi.INSTANCE.GetPeopleAddress(data, length);
            if(address == 1){
                result.put("address", data.getString(0, "gbk"));
            }
            data = new Memory(50);
            int idCode = ReadApi.INSTANCE.GetPeopleIDCode(data, length);
            if(idCode == 1){
                result.put("id", data.getString(0, "gbk"));
            }
            data = new Memory(50);
            int department = ReadApi.INSTANCE.GetDepartment(data, length);
            if(department == 1){
                result.put("department", data.getString(0, "gbk"));
            }
            data = new Memory(20);
            int startDate = ReadApi.INSTANCE.GetStartDate(data, length);
            if(startDate == 1){
                result.put("startDate", data.getString(0, "gbk"));
            }
            data = new Memory(20);
            int endDate = ReadApi.INSTANCE.GetEndDate(data, length);
            if(endDate == 1){
                result.put("endDate", data.getString(0, "gbk"));
            }
        }
        return result;
    }


}
