package org.ecom.WebAnalytics.Output;

import org.ecom.WebAnalytics.ES.Service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics/output")
public class OutputController {
    @Autowired
    private ElasticSearchService esSearchService;

    @GetMapping
    public void analyticsOutput(@RequestBody QueryDTO queryDTO) {

    }
}
