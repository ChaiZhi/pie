/**
 * $Id$
 * Copyright(C) 2010-2016 happyelements.com. All rights reserved.
 */
package org.chaizhi.pie.cs;

/**
 *
 * @author <a href="mailto:zhi.chai@happyelements.com">zhi.chai</a>
 * @version 1.0
 * @since 1.0
 */
public class InterruptedSleepingThreadMain {
	 public static void main(String[] args) throws InterruptedException {
         InterruptedSleepingThread thread = new InterruptedSleepingThread();
         thread.start();
         //Giving 10 seconds to finish the job.
         Thread.sleep(10000);
         //Let me interrupt
         thread.interrupt();
     }
}
