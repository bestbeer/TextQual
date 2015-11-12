package ipeirotis.readability.endpoints;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//import javax.inject.Named;

//import google.api.server.spi.config.Api;
//import google.api.server.spi.config.ApiMethod;
//import google.api.server.spi.config.ApiMethod.HttpMethod;
import ipeirotis.readability.engine.Readability;
import ipeirotis.readability.enums.MetricType;


public class ReadabilityEndpoint {

   
    public Map<MetricType, BigDecimal> get(String text) {
        Map<MetricType, BigDecimal> result = new HashMap<MetricType, BigDecimal>();
        Readability r = new Readability(text);

        for (MetricType metricType : MetricType.values()) {            
            BigDecimal value = new BigDecimal(Double.toString(r.getMetric(metricType)));
            value = value.setScale(3, BigDecimal.ROUND_HALF_UP);
            result.put(metricType, value);
        }

        return result;
    }

}