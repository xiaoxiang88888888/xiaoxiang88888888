package com.xiaoxiang.ticket;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class MainTest extends TestCase {
    @Test
    public void testTicket() throws Exception {
        Main.start();
    }
    /*@Test
    public void testSystemIn() throws Exception {
    	Ticket ticket = new Ticket();
        String value = ticket.readString("请输入:");
        System.out.println(value);
    }*/

}
