package com.lbc.mo.prometheus;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CustomExporter extends Collector {
    @Override
    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<>();
        GaugeMetricFamily labeledGauge =
                new GaugeMetricFamily("module_core_custom_metrics", "custom metrics", Collections.singletonList("labelname"));
        labeledGauge.addMetric(Collections.singletonList("labelvalue"), 1);
        mfs.add(labeledGauge);
        return mfs;
    }
}
