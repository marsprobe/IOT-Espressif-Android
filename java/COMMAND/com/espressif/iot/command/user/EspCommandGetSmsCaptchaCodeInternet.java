package com.espressif.iot.command.user;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.espressif.iot.base.api.EspBaseApiUtil;
import com.espressif.iot.type.net.HeaderPair;

public class EspCommandGetSmsCaptchaCodeInternet implements IEspCommandGetSmsCaptchaCodeInternet
{
    
    @Override
    public boolean doCommandGetSmsCaptchaCode(String phoneNumber, String codeToken)
    {
        String url = URL + phoneNumber;
        String headerKey = Authorization;
        String headerValue = Token + " " + codeToken;
        HeaderPair header = new HeaderPair(headerKey, headerValue);
        
        JSONObject result = EspBaseApiUtil.Get(url, header);
        if (result == null)
        {
            return false;
        }
        
        try
        {
            int httpStatus = result.getInt(Status);
            return httpStatus == HttpStatus.SC_OK;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
