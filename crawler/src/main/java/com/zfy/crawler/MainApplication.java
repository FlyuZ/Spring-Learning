package com.zfy.crawler;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

@Slf4j
public class MainApplication {

    // 程序入口
    public static void main(String[] args) {

        String TARGET_URL="http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/";

        log.info("爬取开始");
        long begin=System.currentTimeMillis();
        Spider.create(new JobProcessor(TARGET_URL))
                .addUrl(TARGET_URL)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new FilePipeline())
                //开启5个线程抓取
                .thread(2)
                //启动爬虫
                .run();

        long end=System.currentTimeMillis();
        log.info("爬取结束");
        long time=end-begin;
        log.info("共使用"+time+"秒");
    }
}
