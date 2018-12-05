package com.ezhomesixgod.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpUtil {
    
	
	/**
	 * 获取请求中的xml数据
	 * @param request
	 * @return
	 */
	public static String recieveData(HttpServletRequest request)
    {
        String inputLine = null;
        // 接收到的数据
        StringBuffer recieveData = new StringBuffer();
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), "UTF-8"));
            while ((inputLine = in.readLine()) != null)
            {
                recieveData.append(inputLine);
            }
        }
        catch (IOException e)
        {
        }
        finally
        {           
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch (IOException e)
            {
            }           
        }
       
        return recieveData.toString();
    }
	
	
}
