package org.concordion.ext.junit;

import org.concordion.ext.junit.footer.TimerSpecificationListenerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        TimerSpecificationListenerTests.class})
public class TestSuite { }
