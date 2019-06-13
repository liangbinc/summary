package com.lbc.mo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExeCmd {
    private static final Log LOG = LogFactory.getLog(ExeCmd.class);

    private ExeCmd() {
    }

    public static String rbhDuCmd(String user) {
        String result = null;
        try {
            String commandStr = "/dfsdata/%s_data";
            String[] cmd = new String[]{"/usr/bin/rbh-du", String.format(commandStr, user)};
            Process ps = Runtime.getRuntime().exec(cmd);

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
            LOG.info(result);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return result;
    }


}
