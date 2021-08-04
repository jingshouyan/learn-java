package com.github.jingshouyan.robin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jingshouyan
 * 2021-08-03 16:27
 **/
public class SmoothWeightRoundRobin {

    private final List<SmoothServer> servers = new ArrayList<>();

    public void add(SmoothServer server) {
        servers.add(server);
    }

    public synchronized SmoothServer get() {
        SmoothServer tmp = null;
        int total = 0;
        for (SmoothServer svr : servers) {
            svr.incCurWeight();
            if (tmp == null || tmp.getCurWeight() < svr.getCurWeight()) {
                tmp = svr;
            }
            total += svr.getCurWeight();
        }

        if (tmp != null) {
            tmp.decCurWeight(total);
        }
        return tmp;

    }

}
