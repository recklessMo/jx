package com.recklessMo.jx.service;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

public interface ReadApi extends Library{
        ReadApi INSTANCE = (ReadApi)
                Native.loadLibrary("Termb",
                        ReadApi.class);

        int CVR_InitComm(int Port);          					 //初始化连接;
        int CVR_Authenticate();									 //卡认证;
        int CVR_Read_Content(int active);		    			 //读卡操作。

        int GetPeopleName(Pointer data, IntByReference len);              //得到姓名信息
        int GetPeopleSex(Pointer data, IntByReference len);               //得到性别信息
        int GetPeopleNation(Pointer data, IntByReference len);            //得到民族信息
        int GetPeopleBirthday(Pointer data, IntByReference len);	        //得到出生日期
        int GetPeopleAddress(Pointer data, IntByReference len);	        //得到地址信息
        int GetPeopleIDCode(Pointer data, IntByReference len);	        //得到卡号信息
        int GetDepartment(Pointer data, IntByReference len);              //得到发证机关信息
        int GetStartDate(Pointer data, IntByReference len);	            //得到有效开始日期
        int GetEndDate(Pointer data, IntByReference len);                 //得到有效截止日期
}
