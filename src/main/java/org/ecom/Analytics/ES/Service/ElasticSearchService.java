package org.ecom.Analytics.ES.Service;

import org.ecom.Analytics.ES.Repository.OrderLogRepository;
import org.ecom.Analytics.ES.Repository.UserActivityLogRepository;
import org.ecom.Analytics.Log.OrderLog;
import org.ecom.Analytics.Log.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchService {
    @Autowired
    private UserActivityLogRepository userActivityLogRepository;
    @Autowired
    private OrderLogRepository orderLogRepository;

    public void saveUserActivityLog(UserActivityLog userActivityLog) {
        userActivityLogRepository.save(userActivityLog);
    }

    public void saveOrderLog(OrderLog orderLog) {
        orderLogRepository.save(orderLog);
    }
}
