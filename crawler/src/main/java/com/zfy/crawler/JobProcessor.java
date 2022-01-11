package com.zfy.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JobProcessor implements PageProcessor {

    private String URL = "";
    private final String year = "2021";
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private final Site site = Site.me().setRetrySleepTime(3000).setSleepTime(1000).setRetryTimes(3);

    public JobProcessor(String URL) {
        this.URL = URL;
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        this.getProvince(page);
        this.getCity(page);
        this.getCounty(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 获取省份的信息
     *
     * @param page 全国省份的页面
     */
    private void getProvince(Page page) {
        List<Map<String, Object>> provinces = page.getHtml().xpath("//tr[@class='provincetr']/td").nodes()
                .stream()
                //过滤文本为空的标签
                .filter(selectable -> selectable.xpath("//a/text()") != null)
                //确定链接
                .filter(selectable -> selectable.links().all().size() != 0)
                //对标签进行操作
                .map(selectable -> {
                    //获取标签内容为:省(直辖市)代码+省名
                    String name = selectable.xpath("//a/text()").toString();
                    //获取该城市的链接
                    String newUrl = selectable.links().all().get(0);
                    //将链接加入到爬取队列中
                    page.addTargetRequest(newUrl);

                    //通过取代链接中的内容获取省(直辖市)级代码
                    String replace = newUrl.replace(URL, "").replace(".html", "");
                    String areaCode = replace + "0000";  //省级代码

                    HashMap<String, Object> map = new HashMap<>();
                    map.put("P_NAME", name);
                    map.put("P_CODE", areaCode);
                    map.put("P_LEVEL", 1);
                    map.put("P_CASCADE", "/");
                    map.put("P_PARENT_CODE", 0L);
                    map.put("P_YEAR", year);

                    return map;
                }).collect(Collectors.toList());


        page.putField("area", provinces);
    }

    /**
     * 获取市级的信息
     *
     * @param page 市级页面
     */
    private void getCity(Page page) {
        //获取市级中的tr下的td的标签列表
        List<Selectable> cityNodes = page.getHtml().xpath("//tr[@class='citytr']/td").nodes();

        List<Map<String, Object>> city = new ArrayList<>();
        cityNodes.forEach(node -> {
            String name = node.xpath("//a/text()").toString();
            if (!Pattern.compile("[0-9]*").matcher(name).matches()) {
                //获取连接    两个连接一样 所以只存第一个
                String newUrl = node.links().all().get(0);
                page.addTargetRequest(newUrl);
                String replace = newUrl.replace(URL, "").replace(".html", "");
                String[] spilt = replace.split("/");
                String parentId = spilt[0] + "0000";
                String areaCode = spilt[spilt.length - 1] + "00";

                HashMap<String, Object> map = new HashMap<>();
                map.put("P_NAME", name);
                map.put("P_CODE", areaCode);
                map.put("P_LEVEL", 2);
                map.put("P_CASCADE", "/" + parentId + "/" + areaCode);
                map.put("P_PARENT_CODE", Long.valueOf(parentId));
                map.put("P_YEAR", year);

                city.add(map);
            }
        });

        page.putField("city", city);
    }

    /**
     * 获取县级数据
     *
     * @param page 县级的页面
     */
    public void getCounty(Page page) {
        List<Map<String, Object>> county = new LinkedList<>();
        List<Selectable> countyNodes = page.getHtml().xpath("//tr[@class='countytr']/td").nodes();
        for (int i = 0; i < countyNodes.size(); i += 2) {
            List<String> code = countyNodes.get(i).xpath("//*/text()").all();
            List<String> name = countyNodes.get(i + 1).xpath("//*/text()").all();
            String countyCode = code.get(0);
            String countyName = name.get(0);
            if (code.size() > 1) {
                countyCode = code.get(1);
                countyName = name.get(1);
                String newUrl = countyNodes.get(i).links().all().get(0);
            }
            countyCode = countyCode.substring(0, 6);
            String parentId = countyCode.substring(0, 4) + "00";
            HashMap<String, Object> map = new HashMap<>();
            map.put("P_NAME", countyName);
            map.put("P_CODE", countyCode);
            map.put("P_LEVEL", 3);
            map.put("P_CASCADE", "/" + countyCode.substring(0, 2) + "0000/" + parentId + "/" + code);
            map.put("P_PARENT_CODE", Long.valueOf(parentId));
            map.put("P_YEAR", year);
            county.add(map);
        }

        page.putField("county", county);
    }
}
