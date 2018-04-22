package com.borisenko.test.steps;

import com.borisenko.test.pages.RegionPage;

/**
 * Created by maxim on 21.04.18.
 */
public class RegionPageSteps  {

    RegionPage regionPage;

    BaseSteps parentSteps;


    public RegionPageSteps setRegionPage(RegionPage regionPage) {
        this.regionPage = regionPage;
        return this;
    }

    public RegionPageSteps setParentSteps(BaseSteps parentSteps) {
        this.parentSteps = parentSteps;
        return this;
    }

    public <T extends BaseSteps> T  selectRegion(String regionName,Class<T> type)
    {
        regionPage.selectRegion(regionName);
        parentSteps.refreshPage();
        return (T) parentSteps;
    }


}
