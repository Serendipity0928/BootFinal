package com.spl.serendipity928.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportMessage {

    /**
     * 示例：{"e":1,"m":"今天已经填报了","d":{}}
     */

    private Integer e;
    private String m;
    private Map<String, String> d;

}
