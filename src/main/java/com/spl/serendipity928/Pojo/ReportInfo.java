package com.spl.serendipity928.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportInfo {
    public long id;
    public String name;
    public String studentID;
    public String email;
    public String cookies;
    public long cookiesExpired;
    public long stime;
    public long etime;
}
