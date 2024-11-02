package com.wky.demo.report;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * auto register report
 *
 * @author wuming
 * @date 2024/7/10/07/10 21:44
 */
@Component
@SuppressWarnings("all")
public class ReportAutoRegistrar {


    @Resource
    private List<AbstractDataQuery> dataQueries;

    @Resource
    private ReportManager reportManager;

    public void register() {
        for (AbstractDataQuery dataQuery : dataQueries) {
            reportManager.register(dataQuery);
        }
    }

}
