package com.feidian.ek.hzaumooc.Bean;

import com.feidian.ek.hzaumooc.R;

/**
 * Created by Administrator on 2016/4/19.
 */
public enum  TeacherSource {
    动物繁殖学(R.array.dwfzx_source),
    ;

    public final int StringID;

    TeacherSource(int stringid) {
        StringID = stringid;
    }
}
