package org.concordion.ext.timing.junit;

import org.concordion.ext.timing.junit.timeformatter.TimeFormatterTests;
import org.concordion.ext.timing.junit.timeformatter.TimerSpecificationListenerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        TimerSpecificationListenerTests.class,
        TimeFormatterTests.class})
public class TestSuite { }
