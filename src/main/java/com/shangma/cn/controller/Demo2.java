package com.shangma.cn.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/30 14:55
 * 文件说明：
 */
public class Demo2 {
    
    /*导包
    *  <dependency>
          <groupId>org.jsoup</groupId>
          <artifactId>jsoup</artifactId>
          <version>1.11.3</version>
        </dependency>
    * */

    public static void main(String[] args) throws IOException {
        StringBuffer buffer = new StringBuffer();
        Document document = Jsoup.connect("https://www.amazon.cn/").get();
        Element searchSelect = document.getElementById("searchDropdownBox");
        Elements options = searchSelect.getElementsByTag("option");
        for (int i = 0; i < options.size(); i++) {
            Element option = options.get(i);
            buffer.append(option.text());
            buffer.append("\r\n");
        }
        String result = buffer.toString();
        FileWriter fileWriter = new FileWriter("E:\\yamaxun.txt");
        fileWriter.write(result);
        fileWriter.close();
    }


}
