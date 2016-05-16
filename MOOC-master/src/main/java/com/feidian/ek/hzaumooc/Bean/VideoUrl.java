package com.feidian.ek.hzaumooc.Bean;

import com.feidian.ek.hzaumooc.R;

public enum VideoUrl {


    动物生理学(R.array.dwslx_video),
    设施园艺学(R.array.dwslx_video),
    动物繁殖学(R.array.dwfzx_video),
    园艺植物生物技术(R.array.dwslx_video),
    魅力汉语(R.array.dwslx_video),
    基因的奥秘(R.array.dwslx_video),
    ;

    public final int StringID;

    VideoUrl(int stringid) {
        StringID = stringid;
    }
}
