package com.fx.freemarker.tags;

import freemarker.template.SimpleHash;

/**
 * Created by bei2love@gmail.com on 15/9/11.
 */
public class FreeTags extends SimpleHash {


    public FreeTags() {
        this.put("selectButton", new SelectButton());
    }
}
