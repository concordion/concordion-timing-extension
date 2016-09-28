package org.concordion.ext.timing.junit;

import org.concordion.ext.timing.junit.timeformatter.SimpleTimeFormatterTests;
import org.concordion.ext.timing.junit.timeformatter.TimerSpecificationListenerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        TimerSpecificationListenerTests.class,
        SimpleTimeFormatterTests.class})
public class TestSuite { }
