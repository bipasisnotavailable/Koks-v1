package Bipas.event.impl;

import Bipas.event.Event;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 21:47
 */
public class SafeWalkEvent extends Event {

    private boolean isSafe;

    public SafeWalkEvent(boolean isSafe) {
        this.isSafe = isSafe;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }
}
